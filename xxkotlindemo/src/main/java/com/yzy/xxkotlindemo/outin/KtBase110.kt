

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  只管消费者
class ConsumerClass1 : Consumer<Animal> {
    override fun consumer(item: Animal) {
        println("消费者 Animal")
    }
}

class ConsumerClass2 : Consumer<Humanity> {
    override fun consumer(item: Humanity) {
        println("消费者 Humanity")
    }
}

class ConsumerClass3 : Consumer<Man> {
    override fun consumer(item: Man) {
        println("消费者 Man")
    }
}

class ConsumerrClass4 : Consumer<WoMan> {
    override fun consumer(item: WoMan) {
        println("消费者 WoMan")
    }
}

// TODO 110-Kotlin语言的in-逆变学习
fun main() {
    val p1 : Consumer<Man> = ConsumerClass1() // ConsumerClass1他本来就是 传递 Animal，居然也可以，因为in
    val p2 : Consumer<WoMan> = ConsumerClass2() // ConsumerClass1他本来就是 传递 Humanity，居然也可以，因为in

    // 默认情况下： 泛型具体出的父类  是不可以赋值给  泛型声明处的子类的
    // in：泛型具体出的父类  是可以赋值给  泛型声明处的子类的

    // 逆变：子类 泛型声明处  可以接收   父类 泛型具体处

      //最终总结:
    // 协变：out 泛型处的父类 = 具体的子类 (子类赋值给父类  合情合理  协和顺变)
    // 逆变：in  泛型处的子类 = 具体的父类 (父类赋值给子类 大逆不道逆变)
}