import java.lang.Exception
import java.lang.Thread.sleep

class Kiosk(name: String) {
    var ticket_num = 1
    var caffe_name = name

    val greeting = "안녕하세요 ${caffe_name}카페 입니다."
    val goodbye = "안녕히가세요."
    val order_menu = "주문 도와드리겠습니다.\n0)그만두기 1)아메리카노 2)라떼 3)카푸치노"
    val order_amount = "수량 입력(1~5)"
    val waiting = "주문이 접수되었습니다. 주문번호는 ${ticket_num}입니다. 잠시만 기다려주세요"
    val making = "뚝딱뚝딱..."
    val ticket_max = 100

    fun take_order():Order{
        var order =  Order(ticket_num, Menu.values()[0], 0)
        try {
            println(order_menu)
            if(ticket_num == ticket_max) ticket_num = 1

            var menu = readLine()!!.toInt()
            when(menu){
                0 -> return order
                in 1..3 ->{
                    order.menu = Menu.values()[menu]
                }
                else -> {
                    show_input_error()
                    return take_order()
                }
            }

            println(order_amount)
            var amount = readLine()!!.toInt()
            when(amount){
                in 1..5 ->{
                    order.amount = amount
                }
                else ->{
                    show_input_error()
                    return take_order()
                }
            }

            ticket_num++
            println(waiting)
        }
        catch (e:Exception) {
            show_input_error()
            take_order()
        }
        return order
    }

    fun greeting(){
        println(greeting)
    }

    fun goodbye(){
        println(goodbye)
    }

    fun serve(order: Order) {
        println("주문하신 ${order.menu} ${order.amount}잔 나왔습니다.")
    }

    fun make(order: Order):Boolean {
        try {
            println(making)
            var time = order.menu.ordinal * 200 * order.amount
            sleep(time.toLong())
            return true
        }catch (e:Exception){
            print(e.printStackTrace())
            return false
        }
    }
}