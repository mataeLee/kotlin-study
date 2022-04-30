import java.lang.Thread.sleep

val quit = 2
val order = 1

fun main(args:Array<String>){
    val kiosk = Kiosk("쿼터 브레드")

    kiosk.greeting()
    while (true){
        show_opts()
        var opt = 0
        try {
            opt = readLine()!!.toInt()
        }
        catch (e:Exception) {
            show_input_error()
            continue
        }

        when(opt){
            order ->{
                var order = kiosk.take_order()
                when(order.menu){
                    Menu.CANCEL ->{
                        println("주문 취소!")
                    }
                    else ->{
                        if(kiosk.make(order)){
                            kiosk.serve(order)
                        }
                    }
                }
                sleep(1000)
            }
            quit ->{
                kiosk.goodbye()
                return
            }
            else -> show_input_error()
        }
    }
}

fun show_opts(){
    println("1) 주문 2) 나가기")
}

fun show_input_error(){
    println("입력 오류!")
}