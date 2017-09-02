package org.kotlin.dsl.json

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

class JsonDlsBuilderUnitTest : FunSpec() {

    init {
        test("should add JSON String Value in JSON Object"){
            val json = json {
                            obj {
                                "description" to "Sample"
                            }
                        }
            json.getValue("description") shouldBe "Sample"
        }

        test("should add JSON Int Value in JSON Object"){
            val json = json {
                            obj {
                                "age" to 12
                            }
                        }
            json.getValue("age") shouldBe 12
        }

        test("should add JSON Float Value in JSON Object"){
            val json = json {
                            obj {
                                "x-coordinate" to 12.98
                            }
                        }
            json.getValue("x-coordinate") shouldBe 12.98
        }

        test("should add JSON Array Value in JSON Object"){
            val countries = arrayOf("India", "US", "UK")
            val json = json {
                            obj {
                                "countries" to countries
                            }
                        }
            json.getValue("countries") shouldBe countries
        }

        test("should add JSON Boolean Value in JSON Object"){
            val json = json {
                            obj {
                                "active" to true
                            }
                        }
            json.getValue("active") shouldBe true
        }

        test("should add NULL Value in JSON Object"){
            val json = json {
                            obj {
                                "email" to null
                            }
                        }
            json.getValue("email") shouldBe null
        }

        test("should return String representation of JSON"){
            val expectedJson = """
                                    {
                                     "description" : "sample"
                                     "active" : true
                                     "countries" : [India, US, UK]
                                     "email" : null
                                     "age" : 12
                                    }
                                """
            val json = json {
                            obj {
                                "description" to "sample"
                                "active"      to true
                                "countries"   to arrayOf("India", "US", "UK")
                                "email"       to null
                                "age"         to 12
                            }
                        }

            json.render() shouldBe expectedJson.trimIndent()
        }
    }

}