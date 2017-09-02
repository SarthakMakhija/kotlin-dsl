package org.kotlin.dsl.json

class Json {

    private lateinit var jsonObject: Obj

    infix fun obj(init: Obj.() -> Unit): Obj {
        val obj = Obj()
        obj.init()
        jsonObject = obj

        return obj
    }

    fun getValue(key: String) = jsonObject.getValue(key)

    fun render(): String = StringBuilder("{").append("\n").append(jsonObject.renderKeyValue()).append("}").toString()
}

class Obj {

    private val keyValue = linkedMapOf<String, Any?>()

    infix fun String.to(value: Any?): Unit {
        keyValue.put(this, value)
    }

    fun renderKeyValue(indent: String = " "): String {
        val sb = StringBuilder()

        for ( (k, v) in keyValue ){
            with(sb) {
                append("$indent$k : ")
                when(v){
                    is String    -> append("\"$v\"")
                    is Array<*>  -> append("[${v.joinToString()}]")
                    else         -> append("$v")
                }
                append("\n")
            }
        }
        return sb.toString()
    }

    fun getValue(key: String) = keyValue[key]
}

fun json(init: Json.() -> Unit): Json {
    val json = Json()
    json.init()
    return json
}