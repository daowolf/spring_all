##### Disruptor 各概念的作用
```
RingBuffer——Disruptor底层数据结构实现，核心类，是线程间交换数据的中转地；
  Sequencer——序号管理器，负责消费者/生产者各自序号、序号栅栏的管理和协调；
  Sequence——序号，声明一个序号，用于跟踪ringbuffer中任务的变化和消费者的消费情况；
  SequenceBarrier——序号栅栏，管理和协调生产者的游标序号和各个消费者的序号，确保生产者不会覆盖消费者未来得及处理的消息，确保存在依赖的消费者之间能够按照正确的顺序处理；
  EventProcessor——事件处理器，监听RingBuffer的事件，并消费可用事件，从RingBuffer读取的事件会交由实际的生产者实现类来消费；它会一直侦听下一个可用的序号，直到该序号对应的事件已经准备好。
  EventHandler——业务处理器，是实际消费者的接口，完成具体的业务逻辑实现，第三方实现该接口；代表着消费者。
  Producer——生产者接口，第三方线程充当该角色，producer向RingBuffer写入事件。
```
##### 什么是ringbuffer
```
它是一个环（首尾相接的环），你可以把它用做在不同上下文（线程）间传递数据的buffer
随着你不停地填充这个buffer（可能也会有相应的读取），这个序号会一直增长，直到绕过这个环。
要找到数组中当前序号指向的元素，可以通过mod操作：以上面的ringbuffer为例（java的mod语法）：12 % 10 = 2。很简单吧。 
事实上，上图中的ringbuffer只有10个槽完全是个意外。如果槽的个数是2的N次方更有利于基于二进制
```
##### RingBuffer底层实现
```
RingBuffer是一个首尾相连的环形数组，所谓首尾相连，是指当RingBuffer上的指针越过数组是上界后，继续从数组头开始遍历。
因此，RingBuffer中至少有一个指针，来表示RingBuffer中的操作位置。另外，指针的自增操作需要做并发控制，
Disruptor和本文的OptimizedQueue都使用CAS的乐观并发控制来保证指针自增的原子性。
Disruptor中的RingBuffer上只有一个指针，表示当前RingBuffer上消息写到了哪里，此外，
每个消费者会维护一个sequence表示自己在RingBuffer上读到哪里，从这个角度讲，Disruptor
中的RingBuffer上实际有消费者数+1个指针。由于我们要实现的是一个单消息单消费的阻塞队列，
只要维护一个读指针（对应消费者）和一个写指针（对应生产者）即可，无论哪个指针，每次读写操作
后都自增一次，一旦越界，即从数组头开始继续读写

```