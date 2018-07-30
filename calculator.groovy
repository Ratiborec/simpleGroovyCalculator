assert proccess('2+2') == '4'
assert proccess('5+12') == '17'
assert proccess('10+3') == '13'
assert proccess('-3+10') == '7'
assert proccess('((80*7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/1000)-63*7)') == '864'
assert proccess('((80+7-30+7))+((4+9+49)+(325+5+13+1000)-63+7)') == '1413'
assert proccess('1+(10-15)*(10-15)') == '26'
assert proccess('(-5)*(-2)') == '10'
assert proccess('((80/7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/1000)-63/7)') == '513'
assert proccess('((80/7-30*7)+(4*9+49)+(325+5*13/1000)-63*7)*3+3*((4*9+49)+(325+5*13/0)-63/7)') == ""
assert proccess('2-2') == '0'
assert proccess('2*2') == '4'
assert proccess('2/2') == '1'
assert proccess('(-3)+(-10)') == '-13'
assert proccess('(-3)*10') == '-30'
assert proccess('(-3)*10') == '-30'
assert proccess('2+2*(5-7)') == '-2'


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
//println(proccess(input))


