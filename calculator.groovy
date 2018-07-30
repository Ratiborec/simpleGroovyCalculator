int count(int left, String operator, int right ) {
    switch (operator){
        case '+':
            return left + right
            break
        case '-':
            return left - right
            break
        case '*':
            return left * right
            break
        case '/':
            try{
                return left / right
            }
            catch (DivisionByZeroError){
                throw DivisionByZeroError
            }
            break
    }
    return 0
}


def proccess (String expression) {
    int result = 0
    String bracket = ""
    String modified = ""
    if (expression.find(/^(\-\d+|\d+)$/)) {
        return expression
    }
        // check numbers like (100) and make 100
    else if( expression.find(/(\()(\d+)(\))/) != null){
        bracket = expression.find(/(\()(\d+)(\))/).find(/(\d+)/)
        modified = expression.replaceFirst(/(\()(\d+)(\))/, bracket.toString() )
    }
        //multiplication and division of numbers (-1)*1  (-1)*(-1)  1*(-1) 1*1
    else if ( expression.find(/(\(\-\d+\)|\d+)([\/\*])(\(\-\d+\)|\d+)/) != null) {
        try{
            result = expression.find(/(\(\-\d+\)|\d+)([\/\*])(\(\-\d+\)|\d+)/) {
                match, left, operator, right ->

                        count(left.find(/(\-\d+|\d+)/).toInteger(), operator, right.find(/(\-\d+|\d+)/).toInteger())
            }.toInteger()
            modified = expression.replaceFirst(/(\(\-\d+\)|\d+)([\/\*])(\(\-\d+\)|\d+)/, result.toString())
        }
        catch (DivisionByZeroError){
            println ("Division by zero.")
            return ""
        }
    }
    else if (expression.find(/(\(\-\d+\)|\d+|\-\d+)([\-\+])(\(\-\d+\)|\d+|\-\d+)(?![\/\*])/) != null){
        result = expression.find(/(\(\-\d+\)|\d+|\-\d+)([\-\+])(\(\-\d+\)|\d+|\-\d+)(?![\/\*])/) {
            match, left, operator, right ->
                count(left.find(/(\-\d+|\d+)/).toInteger(), operator, right.find(/(\-\d+|\d+)/).toInteger())

        }.toInteger()
        modified = expression.replaceFirst(/(\(\-\d+\)|\d+|\-\d+)([\-\+])(\(\-\d+\)|\d+|\-\d+)(?![\/\*])/, result.toString() )
    }
    else {
        modified = expression.replaceFirst(/(\+\-)/, '-')

        if (modified != null) {
            modified = modified.replaceFirst(/(\-\+)/, '-')
        }
        if (modified != null) {
            modified = modified.replaceFirst(/(\-\-)/, '+')
        }
    }
    proccess(modified)
}


//BufferedReader read = new BufferedReader(new InputStreamReader(System.in))
//print "Expression:"
//def input = read.readLine()
/*
String input = '((80*7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/1000)-63*7)' //864
String input2 = '((80+7-30+7))+((4+9+49)+(325+5+13+1000)-63+7)' //1413
String input3 = '((350))+3*((85)+(325)-441)' //257
String input4 = '-4-4' // -8
String input5 = '(-5)*(-2)' //10
String input6 = '1+(10-15)*(10-15)' //26
String input7 = "((80/7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/1000)-63/7)" //513\
String input8= '((80/7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/0)-63/7)'
*/
/*
println(proccess(input))
println(proccess(input2))
println(proccess(input3))
println(proccess(input4))
println(proccess(input5))
println(proccess(input6))
println(proccess(input7))
println(proccess(input8))
*/


