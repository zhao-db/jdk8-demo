# Java 8 Optional 与函数式接口完全指南

---

## 一、Optional 类详解

### 1.1 什么是 Optional？

`Optional<T>` 是 Java 8 引入的一个容器类，用于表示一个值存在或不存在。它的主要目的是：

- **避免 NullPointerException**：优雅地处理可能为 null 的值
- **提高代码可读性**：明确表达返回值可能为空的语义
- **支持函数式编程**：提供丰富的链式操作方法

### 1.2 创建 Optional 对象

```java
// 1. 创建一个空的 Optional
Optional<String> empty = Optional.empty();

// 2. 创建一个非空的 Optional（如果值为 null 会抛出 NullPointerException）
Optional<String> nonNull = Optional.of("Hello");

// 3. 创建一个可能为空的 Optional（推荐使用）
Optional<String> nullable = Optional.ofNullable(getString()); // getString() 可能返回 null
```

### 1.3 判断值是否存在

```java
Optional<String> optional = Optional.ofNullable("Hello");

// isPresent() - 判断值是否存在
if (optional.isPresent()) {
    System.out.println("值存在: " + optional.get());
}

// isEmpty() - Java 11+ 判断值是否为空
if (optional.isEmpty()) {
    System.out.println("值为空");
}

// ifPresent() - 如果存在则执行操作（推荐）
optional.ifPresent(value -> System.out.println("值存在: " + value));

// ifPresentOrElse() - Java 9+ 存在执行操作，不存在执行另一个操作
optional.ifPresentOrElse(
    value -> System.out.println("值: " + value),
    () -> System.out.println("值不存在")
);
```

### 1.4 获取值

```java
Optional<String> optional = Optional.ofNullable(getValue());

// 1. get() - 直接获取（不推荐，可能抛出 NoSuchElementException）
String value1 = optional.get();

// 2. orElse() - 不存在时返回默认值（推荐）
String value2 = optional.orElse("默认值");

// 3. orElseGet() - 不存在时通过 Supplier 生成默认值（推荐，延迟计算）
String value3 = optional.orElseGet(() -> generateDefault());

// 4. orElseThrow() - 不存在时抛出异常
String value4 = optional.orElseThrow(() -> new RuntimeException("值不能为空"));

// 5. orElseThrow() - Java 10+ 不存在时抛出 NoSuchElementException
String value5 = optional.orElseThrow();
```

#### orElse() vs orElseGet() 的区别

```java
// orElse() - 无论 Optional 是否有值，都会执行 orElse 中的代码
String result1 = Optional.of("存在").orElse(expensiveOperation()); // expensiveOperation() 会被调用！

// orElseGet() - 只有 Optional 为空时，才会执行 Supplier
String result2 = Optional.of("存在").orElseGet(() -> expensiveOperation()); // expensiveOperation() 不会被调用
```

### 1.5 转换与过滤

```java
Optional<User> userOptional = findUserById(1L);

// 1. map() - 转换值
Optional<String> nameOptional = userOptional.map(User::getName);
String name = userOptional.map(User::getName).orElse("Unknown");

// 2. flatMap() - 用于处理返回 Optional 的方法，避免嵌套
Optional<String> addressOptional = userOptional
    .flatMap(User::getAddress)  // getAddress() 返回 Optional<Address>
    .map(Address::getCity);

// 3. filter() - 过滤值
Optional<User> adultUser = userOptional.filter(user -> user.getAge() >= 18);
```

### 1.6 链式操作示例

```java
// 传统写法
public String getCarInsuranceName(Person person) {
    if (person != null) {
        Car car = person.getCar();
        if (car != null) {
            Insurance insurance = car.getInsurance();
            if (insurance != null) {
                return insurance.getName();
            }
        }
    }
    return "Unknown";
}

// Optional 优雅写法
public String getCarInsuranceName(Person person) {
    return Optional.ofNullable(person)
        .map(Person::getCar)
        .map(Car::getInsurance)
        .map(Insurance::getName)
        .orElse("Unknown");
}
```

### 1.7 Optional 最佳实践

| ✅ 推荐做法 | ❌ 不推荐做法 |
|-----------|-------------|
| 用作方法返回值 | 用作方法参数 |
| 使用 `orElse`/`orElseGet` | 使用 `get()` 直接获取 |
| 使用 `map`/`flatMap` 链式处理 | 使用 `isPresent()` + `get()` |
| 返回 `Optional.empty()` | 返回 `null` |

---

## 二、函数式接口详解

### 2.1 什么是函数式接口？

函数式接口是**只包含一个抽象方法**的接口，可以使用 `@FunctionalInterface` 注解标识。函数式接口是 Lambda 表达式的基础。

```java
@FunctionalInterface
public interface MyFunction {
    void execute();  // 唯一的抽象方法
    
    // 可以有默认方法
    default void defaultMethod() {
        System.out.println("默认方法");
    }
    
    // 可以有静态方法
    static void staticMethod() {
        System.out.println("静态方法");
    }
}
```

### 2.2 核心函数式接口

Java 8 在 `java.util.function` 包中提供了大量内置函数式接口：

| 接口 | 抽象方法 | 说明 | 使用场景 |
|------|---------|------|---------|
| `Supplier<T>` | `T get()` | 无参数，返回结果 | 工厂方法、延迟计算 |
| `Consumer<T>` | `void accept(T t)` | 接收参数，无返回值 | 遍历、日志输出 |
| `Function<T, R>` | `R apply(T t)` | 接收参数，返回结果 | 转换、映射 |
| `Predicate<T>` | `boolean test(T t)` | 接收参数，返回布尔值 | 过滤、条件判断 |
| `BiFunction<T, U, R>` | `R apply(T t, U u)` | 接收两个参数，返回结果 | 合并操作 |
| `BiConsumer<T, U>` | `void accept(T t, U u)` | 接收两个参数，无返回值 | 遍历 Map |
| `BiPredicate<T, U>` | `boolean test(T t, U u)` | 接收两个参数，返回布尔值 | 双参数条件 |
| `UnaryOperator<T>` | `T apply(T t)` | 接收参数，返回同类型结果 | 字符串处理 |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | 接收两个同类型参数，返回同类型结果 | 归约操作 |

### 2.3 Supplier<T> - 供给型接口

**特点**：无参数，返回一个结果

```java
// 基本使用
Supplier<String> supplier = () -> "Hello World";
String result = supplier.get();  // "Hello World"

// 工厂模式
Supplier<User> userFactory = User::new;
User user = userFactory.get();  // 创建新用户

// 延迟计算
Supplier<Double> randomSupplier = Math::random;
System.out.println(randomSupplier.get());  // 每次调用生成新的随机数

// 结合 Optional 使用
String name = Optional.ofNullable(getName())
    .orElseGet(() -> "默认名称");  // Supplier 只在需要时执行

// 实际应用：日志延迟计算
public void log(Supplier<String> messageSupplier) {
    if (isDebugEnabled()) {
        System.out.println(messageSupplier.get());  // 只有开启 debug 才计算
    }
}
```

### 2.4 Consumer<T> - 消费型接口

**特点**：接收一个参数，无返回值

```java
// 基本使用
Consumer<String> printer = message -> System.out.println(message);
printer.accept("Hello");  // 输出: Hello

// 方法引用
Consumer<String> printer2 = System.out::println;
printer2.accept("World");

// 遍历集合
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println("Hello, " + name));

// andThen - 链式调用
Consumer<String> first = s -> System.out.print("First: " + s);
Consumer<String> second = s -> System.out.println(", Second: " + s);
Consumer<String> combined = first.andThen(second);
combined.accept("test");  // 输出: First: test, Second: test

// 实际应用：Builder 模式
public User createUser(Consumer<User> userConfigurer) {
    User user = new User();
    userConfigurer.accept(user);
    return user;
}

User user = createUser(u -> {
    u.setName("张三");
    u.setAge(25);
    u.setEmail("zhangsan@example.com");
});
```

### 2.5 Function<T, R> - 函数型接口

**特点**：接收一个参数，返回一个结果

```java
// 基本使用
Function<String, Integer> lengthFunc = s -> s.length();
Integer length = lengthFunc.apply("Hello");  // 5

// 方法引用
Function<String, Integer> parseFunc = Integer::parseInt;
Integer num = parseFunc.apply("123");  // 123

// andThen - 先执行当前函数，再执行参数函数
Function<Integer, Integer> doubleIt = x -> x * 2;
Function<Integer, Integer> addTen = x -> x + 10;
Function<Integer, Integer> combined = doubleIt.andThen(addTen);
Integer result = combined.apply(5);  // (5 * 2) + 10 = 20

// compose - 先执行参数函数，再执行当前函数
Function<Integer, Integer> composed = doubleIt.compose(addTen);
Integer result2 = composed.apply(5);  // (5 + 10) * 2 = 30

// identity - 返回输入本身
Function<String, String> identity = Function.identity();
String same = identity.apply("test");  // "test"

// 结合 Stream 使用
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());  // [5, 3, 7]

// 实际应用：策略模式
public <T, R> R process(T input, Function<T, R> processor) {
    return processor.apply(input);
}

String upper = process("hello", String::toUpperCase);  // "HELLO"
Integer length = process("hello", String::length);     // 5
```

### 2.6 Predicate<T> - 断言型接口

**特点**：接收一个参数，返回布尔值

```java
// 基本使用
Predicate<String> isEmpty = s -> s.isEmpty();
boolean result = isEmpty.test("");  // true

// 方法引用
Predicate<String> isBlank = String::isBlank;

// and - 与操作
Predicate<String> notEmpty = s -> !s.isEmpty();
Predicate<String> shorterThan10 = s -> s.length() < 10;
Predicate<String> combined = notEmpty.and(shorterThan10);
boolean valid = combined.test("Hello");  // true

// or - 或操作
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> isNegative = n -> n < 0;
Predicate<Integer> evenOrNegative = isEven.or(isNegative);
boolean match = evenOrNegative.test(-3);  // true

// negate - 取反
Predicate<String> notBlank = isBlank.negate();
boolean notEmpty2 = notBlank.test("Hello");  // true

// isEqual - 相等判断
Predicate<String> equalsHello = Predicate.isEqual("Hello");
boolean equals = equalsHello.test("Hello");  // true

// 结合 Stream 使用
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());  // [2, 4, 6, 8, 10]

// 实际应用：动态过滤条件
public List<User> filterUsers(List<User> users, Predicate<User> condition) {
    return users.stream()
        .filter(condition)
        .collect(Collectors.toList());
}

List<User> adults = filterUsers(users, u -> u.getAge() >= 18);
List<User> activeAdults = filterUsers(users, 
    u -> u.getAge() >= 18 && u.isActive());
```

### 2.7 BiFunction<T, U, R> - 双参数函数接口

**特点**：接收两个参数，返回一个结果

```java
// 基本使用
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Integer sum = add.apply(10, 20);  // 30

// 字符串拼接
BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
String fullName = concat.apply("John", "Doe");  // "John Doe"

// andThen
BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
Function<Integer, String> toString = i -> "结果: " + i;
BiFunction<Integer, Integer, String> multiplyAndFormat = multiply.andThen(toString);
String result = multiplyAndFormat.apply(5, 3);  // "结果: 15"

// 实际应用：Map 的 computeIfAbsent
Map<String, Integer> wordCount = new HashMap<>();
BiFunction<String, Integer, Integer> increment = (key, oldValue) -> 
    oldValue == null ? 1 : oldValue + 1;
wordCount.compute("hello", increment);  // hello -> 1
wordCount.compute("hello", increment);  // hello -> 2
```

### 2.8 BiConsumer<T, U> - 双参数消费接口

**特点**：接收两个参数，无返回值

```java
// 基本使用
BiConsumer<String, Integer> printInfo = (name, age) -> 
    System.out.println(name + " is " + age + " years old");
printInfo.accept("Alice", 25);  // Alice is 25 years old

// 遍历 Map
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 87);
scores.put("Charlie", 92);

scores.forEach((name, score) -> 
    System.out.println(name + ": " + score));

// andThen 链式调用
BiConsumer<StringBuilder, String> append = StringBuilder::append;
BiConsumer<StringBuilder, String> appendWithSpace = append.andThen(
    (sb, s) -> sb.append(" "));
StringBuilder sb = new StringBuilder();
appendWithSpace.accept(sb, "Hello");
appendWithSpace.accept(sb, "World");
System.out.println(sb);  // "Hello World "
```

### 2.9 UnaryOperator<T> 和 BinaryOperator<T>

```java
// UnaryOperator - 一元操作符（输入输出类型相同）
UnaryOperator<Integer> square = x -> x * x;
Integer result = square.apply(5);  // 25

UnaryOperator<String> toUpper = String::toUpperCase;
String upper = toUpper.apply("hello");  // "HELLO"

// List.replaceAll 使用 UnaryOperator
List<String> names = new ArrayList<>(Arrays.asList("alice", "bob"));
names.replaceAll(String::toUpperCase);  // [ALICE, BOB]

// BinaryOperator - 二元操作符（输入输出类型相同）
BinaryOperator<Integer> max = Integer::max;
Integer maxValue = max.apply(10, 20);  // 20

BinaryOperator<String> join = (s1, s2) -> s1 + ", " + s2;
String joined = join.apply("Hello", "World");  // "Hello, World"

// Stream 的 reduce 使用 BinaryOperator
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Integer sum = numbers.stream()
    .reduce(0, Integer::sum);  // 15

// minBy / maxBy
BinaryOperator<Integer> minOperator = BinaryOperator.minBy(Integer::compareTo);
Integer min = minOperator.apply(10, 5);  // 5
```

### 2.10 基本类型特化接口

为了避免自动装箱带来的性能损失，Java 提供了基本类型特化的函数式接口：

| 通用接口 | int 特化 | long 特化 | double 特化 |
|---------|---------|----------|-------------|
| `Supplier<T>` | `IntSupplier` | `LongSupplier` | `DoubleSupplier` |
| `Consumer<T>` | `IntConsumer` | `LongConsumer` | `DoubleConsumer` |
| `Function<T,R>` | `IntFunction<R>` | `LongFunction<R>` | `DoubleFunction<R>` |
| `Predicate<T>` | `IntPredicate` | `LongPredicate` | `DoublePredicate` |
| `UnaryOperator<T>` | `IntUnaryOperator` | `LongUnaryOperator` | `DoubleUnaryOperator` |
| `BinaryOperator<T>` | `IntBinaryOperator` | `LongBinaryOperator` | `DoubleBinaryOperator` |

```java
// 使用特化接口避免装箱
IntSupplier intSupplier = () -> 42;
int value = intSupplier.getAsInt();  // 直接返回 int，无需拆箱

IntPredicate isPositive = n -> n > 0;
boolean positive = isPositive.test(10);  // true

IntUnaryOperator doubleIt = n -> n * 2;
int doubled = doubleIt.applyAsInt(5);  // 10

IntBinaryOperator add = (a, b) -> a + b;
int sum = add.applyAsInt(3, 4);  // 7

// 类型转换接口
IntToDoubleFunction intToDouble = n -> n * 1.5;
double result = intToDouble.applyAsDouble(10);  // 15.0

ToIntFunction<String> stringLength = String::length;
int length = stringLength.applyAsInt("Hello");  // 5
```

---

## 三、方法引用

方法引用是 Lambda 表达式的简化写法，当 Lambda 表达式仅仅调用一个已存在的方法时使用。

### 3.1 四种方法引用类型

| 类型 | 语法 | Lambda 等价写法 |
|------|------|---------------|
| 静态方法引用 | `ClassName::staticMethod` | `(args) -> ClassName.staticMethod(args)` |
| 实例方法引用 | `instance::method` | `(args) -> instance.method(args)` |
| 对象方法引用 | `ClassName::method` | `(obj, args) -> obj.method(args)` |
| 构造方法引用 | `ClassName::new` | `(args) -> new ClassName(args)` |

### 3.2 示例

```java
// 1. 静态方法引用
Function<String, Integer> parser = Integer::parseInt;
// 等价于: s -> Integer.parseInt(s)

// 2. 实例方法引用
String prefix = "Hello, ";
Function<String, String> greeter = prefix::concat;
// 等价于: s -> prefix.concat(s)

// 3. 对象方法引用
Function<String, String> upper = String::toUpperCase;
// 等价于: s -> s.toUpperCase()

BiPredicate<String, String> contains = String::contains;
// 等价于: (s1, s2) -> s1.contains(s2)

// 4. 构造方法引用
Supplier<ArrayList<String>> listFactory = ArrayList::new;
// 等价于: () -> new ArrayList<>()

Function<Integer, ArrayList<String>> listWithSize = ArrayList::new;
// 等价于: size -> new ArrayList<>(size)

// 数组构造引用
Function<Integer, String[]> arrayFactory = String[]::new;
String[] array = arrayFactory.apply(5);  // 创建长度为 5 的字符串数组
```

---

## 四、综合实战示例

### 4.1 用户服务示例

```java
public class UserService {
    private Map<Long, User> userDatabase = new HashMap<>();
    
    // 使用 Optional 返回可能为空的结果
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userDatabase.get(id));
    }
    
    // 使用 Predicate 进行过滤
    public List<User> findByCondition(Predicate<User> condition) {
        return userDatabase.values().stream()
            .filter(condition)
            .collect(Collectors.toList());
    }
    
    // 使用 Consumer 进行批量操作
    public void updateAll(Consumer<User> updater) {
        userDatabase.values().forEach(updater);
    }
    
    // 使用 Function 进行转换
    public <R> List<R> mapUsers(Function<User, R> mapper) {
        return userDatabase.values().stream()
            .map(mapper)
            .collect(Collectors.toList());
    }
    
    // 使用 Supplier 延迟创建
    public User getOrCreate(Long id, Supplier<User> userSupplier) {
        return findById(id).orElseGet(userSupplier);
    }
}

// 使用示例
UserService service = new UserService();

// 链式 Optional 操作
String email = service.findById(1L)
    .map(User::getEmail)
    .filter(e -> e.endsWith("@company.com"))
    .orElse("default@company.com");

// 复杂条件查询
List<User> activeAdults = service.findByCondition(
    user -> user.getAge() >= 18 && user.isActive()
);

// 批量更新
service.updateAll(user -> user.setLastLoginTime(LocalDateTime.now()));

// 转换为 DTO
List<UserDTO> dtos = service.mapUsers(user -> new UserDTO(
    user.getId(), 
    user.getName(), 
    user.getEmail()
));
```

### 4.2 链式构建器模式

```java
public class EmailBuilder {
    private String from;
    private String to;
    private String subject;
    private String body;
    
    public static EmailBuilder create() {
        return new EmailBuilder();
    }
    
    public EmailBuilder with(Consumer<EmailBuilder> builderConsumer) {
        builderConsumer.accept(this);
        return this;
    }
    
    // Getters and Setters...
    
    public Email build() {
        return new Email(from, to, subject, body);
    }
}

// 使用
Email email = EmailBuilder.create()
    .with(b -> b.setFrom("sender@example.com"))
    .with(b -> b.setTo("receiver@example.com"))
    .with(b -> b.setSubject("Hello"))
    .with(b -> b.setBody("This is the body"))
    .build();
```

### 4.3 策略模式与函数式接口

```java
public class PriceCalculator {
    // 使用 Function 作为策略
    private Function<BigDecimal, BigDecimal> discountStrategy;
    
    public PriceCalculator(Function<BigDecimal, BigDecimal> discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    public BigDecimal calculate(BigDecimal originalPrice) {
        return discountStrategy.apply(originalPrice);
    }
    
    // 预定义策略
    public static Function<BigDecimal, BigDecimal> noDiscount() {
        return Function.identity();
    }
    
    public static Function<BigDecimal, BigDecimal> percentageDiscount(int percent) {
        BigDecimal multiplier = BigDecimal.ONE.subtract(
            BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100))
        );
        return price -> price.multiply(multiplier);
    }
    
    public static Function<BigDecimal, BigDecimal> fixedDiscount(BigDecimal amount) {
        return price -> price.subtract(amount).max(BigDecimal.ZERO);
    }
}

// 使用
PriceCalculator calculator = new PriceCalculator(
    PriceCalculator.percentageDiscount(20)  // 8折
);
BigDecimal finalPrice = calculator.calculate(new BigDecimal("100"));  // 80
```

---

## 五、总结

### Optional 核心要点
1. 使用 `Optional.ofNullable()` 创建可能为空的值
2. 使用 `map()` 和 `flatMap()` 进行链式转换
3. 使用 `orElse()` 或 `orElseGet()` 提供默认值
4. 避免使用 `get()` 直接获取值

### 函数式接口核心要点
1. `Supplier<T>`: 无参返回值 - 工厂、延迟计算
2. `Consumer<T>`: 有参无返回 - 遍历、副作用操作
3. `Function<T,R>`: 有参有返回 - 转换、映射
4. `Predicate<T>`: 有参返回布尔 - 过滤、条件判断
5. 方法引用是 Lambda 的简化形式
6. 使用基本类型特化接口避免装箱开销

### 函数式编程优势
- ✅ 代码更简洁、可读性更高
- ✅ 便于组合和复用
- ✅ 支持延迟计算
- ✅ 更容易并行化
- ✅ 减少空指针异常
