package org.kotlin.dsl.json

class Json {

    private lateinit var jsonObject: Obj

    infix fun root(init: Obj.() -> Unit): Obj {
        val obj    = Obj()
        obj.init()
        jsonObject = obj

        return obj
    }

    fun getValue(key: String): Any? = jsonObject.getValue(key)

    fun render(): String            = jsonObject.toJsonString()
}

class Obj {

    private val keyValue = linkedMapOf<String, Any?>()

    infix fun String.to(value: String?): Unit {
        keyValue.put(this, value)
    }

    infix fun String.to(value: Int?): Unit {
        keyValue.put(this, value)
    }

    infix fun String.to(value: Double?): Unit {
        keyValue.put(this, value)
    }

    infix fun String.to(value: Boolean?): Unit {
        keyValue.put(this, value)
    }

    infix fun String.to(value: Array<*>?): Unit {
        keyValue.put(this, value)
    }

    infix fun String.to(value: Obj?): Unit {
        keyValue.put(this, value)
    }

    infix fun obj(init: Obj.() -> Unit): Obj {
        val obj = Obj()
        obj.init()

        return obj
    }

    fun toJsonString(indent: String = " "): String {
        fun toJsonStringNested(nested: Obj, indent: String): String {
            val sb = StringBuilder().append("{").append("\n")

            for ( (k, v) in nested.keyValue ){
                with(sb) {
                    append("""$indent"$k" : """)
                    when(v){
                        is String    -> append(""""$v"""")
                        is Array<*>  -> append("[${v.joinToString()}]")
                        is Obj       -> append(toJsonStringNested(nested = v, indent = indent + " "))
                        else         -> append("$v")
                    }
                    append("\n")
                }
            }
            return sb.append("$indent}").toString()
        }

        return toJsonStringNested(this, indent = indent)
    }

    fun getValue(key: String) = keyValue[key]

    override fun toString(): String {
        return "Obj(keyValue=$keyValue)"
    }
}

fun json(init: Json.() -> Unit): Json {
    val json = Json()
    json.init()
    return json
}