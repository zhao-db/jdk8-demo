# CompletableFuture 完全指南

## 1. 简介

`CompletableFuture` 是 Java 8 引入的一个强大的异步编程工具类，实现了 `Future` 和 `CompletionStage` 接口。它提供了丰富的 API 来处理异步计算、组合多个异步操作、处理异常等场景。

### 为什么使用 CompletableFuture？

相比传统的 `Future`，`CompletableFuture` 具有以下优势：
- **非阻塞回调**：可以通过回调方式处理结果，无需阻塞等待
- **链式调用**：支持多个异步操作的链式组合
- **异常处理**：提供完善的异常处理机制
- **组合操作**：可以组合多个 CompletableFuture 的结果

---

## 2. 创建 CompletableFuture

### 2.1 supplyAsync - 有返回值的异步任务

```java
// 使用默认的 ForkJoinPool
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // 模拟耗时操作
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return "Hello, CompletableFuture!";
});

// 获取结果（阻塞）
String result = future.get();
System.out.println(result); // 输出: Hello, CompletableFuture!
```

### 2.2 runAsync - 无返回值的异步任务

```java
CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    System.out.println("任务执行完毕！");
});

// 等待任务完成
future.join();
```

### 2.3 使用自定义线程池

```java
ExecutorService executor = Executors.newFixedThreadPool(10);

// supplyAsync 使用自定义线程池
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
    return "使用自定义线程池";
}, executor);

// runAsync 使用自定义线程池
CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
    System.out.println("在自定义线程池中执行");
}, executor);

// 记得关闭线程池
executor.shutdown();
```

### 2.4 手动创建和完成

```java
CompletableFuture<String> future = new CompletableFuture<>();

// 在其他线程中手动完成
new Thread(() -> {
    try {
        Thread.sleep(1000);
        future.complete("手动完成的结果");
    } catch (InterruptedException e) {
        future.completeExceptionally(e);
    }
}).start();

System.out.println(future.get()); // 手动完成的结果
```

---

## 3. 获取结果

### 3.1 get() vs join()

| 方法 | 异常处理 | 使用场景 |
|------|---------|---------|
| `get()` | 抛出受检异常 `ExecutionException` | 需要显式处理异常 |
| `join()` | 抛出非受检异常 `CompletionException` | 链式调用更方便 |

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

// 方式1: get() - 需要处理受检异常
try {
    String result = future.get();
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}

// 方式2: get() 带超时
try {
    String result = future.get(5, TimeUnit.SECONDS);
} catch (InterruptedException | ExecutionException | TimeoutException e) {
    e.printStackTrace();
}

// 方式3: join() - 更简洁
String result = future.join();

// 方式4: getNow() - 立即获取，如果未完成返回默认值
String result = future.getNow("默认值");
```

---

## 4. 结果转换

### 4.1 thenApply - 转换结果（有返回值）

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> 100)
    .thenApply(num -> num * 2)       // 200
    .thenApply(num -> "结果: " + num); // "结果: 200"

System.out.println(future.join()); // 结果: 200
```

### 4.2 thenAccept - 消费结果（无返回值）

```java
CompletableFuture.supplyAsync(() -> "Hello World")
    .thenAccept(result -> System.out.println("收到结果: " + result));
```

### 4.3 thenRun - 执行后续操作（不关心结果）

```java
CompletableFuture.supplyAsync(() -> "Hello World")
    .thenRun(() -> System.out.println("前一个任务完成了，但我不关心结果"));
```

### 4.4 Async 变体

每个转换方法都有对应的 Async 版本，可以在不同线程中执行：

```java
CompletableFuture.supplyAsync(() -> 100)
    .thenApplyAsync(num -> num * 2)  // 可能在不同线程执行
    .thenAcceptAsync(System.out::println);
```

---

## 5. 组合多个 CompletableFuture

### 5.1 thenCompose - 串行组合（扁平化）

当一个异步操作依赖另一个异步操作的结果时使用：

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> 1)
    .thenCompose(num -> CompletableFuture.supplyAsync(() -> "Number: " + num));

System.out.println(future.join()); // Number: 1
```

### 5.2 thenCombine - 并行组合（合并结果）

两个任务并行执行，都完成后合并结果：

```java
CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
    sleep(1000);
    return 42;
});

CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
    sleep(1500);
    return 13;
});

// 两个任务并行执行，结果合并
CompletableFuture<Integer> resultFuture = future1.thenCombine(future2, (a, b) -> a + b);

System.out.println(resultFuture.join()); // 55，总耗时约 1.5 秒（而非 2.5 秒）
```

### 5.3 allOf - 等待所有任务完成

```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "任务1");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "任务2");
CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "任务3");

CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

// 等待所有任务完成
allFutures.join();

// 获取所有结果
List<String> results = Stream.of(future1, future2, future3)
    .map(CompletableFuture::join)
    .collect(Collectors.toList());

System.out.println(results); // [任务1, 任务2, 任务3]
```

### 5.4 anyOf - 任意一个任务完成

```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
    sleep(2000);
    return "慢任务";
});

CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
    sleep(500);
    return "快任务";
});

CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);

System.out.println(anyFuture.join()); // 快任务（最先完成的）
```

---

## 6. 异常处理

### 6.1 exceptionally - 异常恢复

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    if (true) {
        throw new RuntimeException("计算出错");
    }
    return 100;
}).exceptionally(ex -> {
    System.out.println("发生异常: " + ex.getMessage());
    return 0; // 返回默认值
});

System.out.println(future.join()); // 0
```

### 6.2 handle - 同时处理结果和异常

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    return 42 / 0; // 抛出异常
}).handle((result, ex) -> {
    if (ex != null) {
        System.out.println("处理异常: " + ex.getMessage());
        return -1;
    }
    return result;
});

System.out.println(future.join()); // -1
```

### 6.3 whenComplete - 完成时回调

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 42)
    .whenComplete((result, ex) -> {
        if (ex != null) {
            System.out.println("发生异常: " + ex.getMessage());
        } else {
            System.out.println("结果: " + result);
        }
    });
```

### 6.4 异常链处理示例

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    sleep(1000);
    return 42;
}).thenCompose(value -> CompletableFuture.supplyAsync(() -> {
    return value / 0; // 故意抛出异常
})).exceptionally(e -> {
    System.out.println("发生了异常");
    System.out.println("异常信息为：" + e.getMessage());
    return null;
});

System.out.println(future.join()); // null
```

---

## 7. 实用技巧

### 7.1 超时控制（Java 9+）

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    sleep(5000);
    return "结果";
}).orTimeout(2, TimeUnit.SECONDS)  // 2秒超时
  .exceptionally(ex -> "超时默认值");
```

### 7.2 带默认值的超时（Java 9+）

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    sleep(5000);
    return "结果";
}).completeOnTimeout("默认值", 2, TimeUnit.SECONDS);
```

### 7.3 检查任务状态

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

// 检查是否完成
boolean isDone = future.isDone();

// 检查是否异常完成
boolean isCompletedExceptionally = future.isCompletedExceptionally();

// 检查是否被取消
boolean isCancelled = future.isCancelled();
```

### 7.4 取消任务

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    sleep(5000);
    return "结果";
});

// 取消任务
boolean cancelled = future.cancel(true);
```

---

## 8. 最佳实践

### 8.1 使用自定义线程池

```java
// 推荐：使用自定义线程池，避免共用 ForkJoinPool
private static final ExecutorService executor = new ThreadPoolExecutor(
    10, 20, 60L, TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(100),
    new ThreadPoolExecutor.CallerRunsPolicy()
);

CompletableFuture.supplyAsync(() -> doSomething(), executor);
```

### 8.2 合理处理异常

```java
// 推荐：每个关键步骤都处理异常
CompletableFuture<Result> future = CompletableFuture
    .supplyAsync(() -> fetchData())
    .thenApply(data -> process(data))
    .exceptionally(ex -> {
        log.error("处理失败", ex);
        return defaultResult();
    });
```

### 8.3 避免过长的链式调用

```java
// 不推荐：过长的链式调用难以调试
future.thenApply(...).thenApply(...).thenApply(...).thenApply(...)...

// 推荐：适当拆分
CompletableFuture<A> step1 = doStep1();
CompletableFuture<B> step2 = step1.thenApply(this::doStep2);
CompletableFuture<C> step3 = step2.thenApply(this::doStep3);
```

---

## 9. API 方法速查表

| 方法 | 描述 | 返回类型 |
|-----|------|---------|
| `supplyAsync(Supplier)` | 异步执行有返回值的任务 | `CompletableFuture<T>` |
| `runAsync(Runnable)` | 异步执行无返回值的任务 | `CompletableFuture<Void>` |
| `thenApply(Function)` | 转换结果 | `CompletableFuture<U>` |
| `thenAccept(Consumer)` | 消费结果 | `CompletableFuture<Void>` |
| `thenRun(Runnable)` | 执行操作 | `CompletableFuture<Void>` |
| `thenCompose(Function)` | 串行组合 | `CompletableFuture<U>` |
| `thenCombine(CF, BiFunction)` | 并行组合 | `CompletableFuture<V>` |
| `allOf(CF...)` | 等待所有完成 | `CompletableFuture<Void>` |
| `anyOf(CF...)` | 任一完成 | `CompletableFuture<Object>` |
| `exceptionally(Function)` | 异常处理 | `CompletableFuture<T>` |
| `handle(BiFunction)` | 处理结果和异常 | `CompletableFuture<U>` |
| `whenComplete(BiConsumer)` | 完成时回调 | `CompletableFuture<T>` |

---

## 10. 完整示例

### 模拟电商下单场景

```java
public class OrderService {
    
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    
    public CompletableFuture<Order> createOrder(Long userId, Long productId) {
        // 并行查询用户信息和商品信息
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(
            () -> userService.getUser(userId), executor);
        
        CompletableFuture<Product> productFuture = CompletableFuture.supplyAsync(
            () -> productService.getProduct(productId), executor);
        
        // 合并结果创建订单
        return userFuture.thenCombine(productFuture, (user, product) -> {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            order.setCreateTime(new Date());
            return order;
        }).thenCompose(order -> {
            // 保存订单
            return CompletableFuture.supplyAsync(
                () -> orderRepository.save(order), executor);
        }).thenApply(savedOrder -> {
            // 发送通知
            notificationService.sendOrderConfirmation(savedOrder);
            return savedOrder;
        }).exceptionally(ex -> {
            log.error("创建订单失败", ex);
            throw new OrderException("创建订单失败: " + ex.getMessage());
        });
    }
}
```

---

## 参考资料

- [Java 8 CompletableFuture 官方文档](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html)
- [CompletionStage 接口文档](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html)

