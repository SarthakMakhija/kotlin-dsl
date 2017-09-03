package org.kotlin.dsl.json

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

class JsonDlsBuilderUnitTest : FunSpec() {

    init {
        test("should add JSON String Value in JSON Object"){
            val json = json {
                            root {
                                "description" to "Sample"
                            }
                        }
            json.getValue("description") shouldBe "Sample"
        }

        test("should add JSON Int Value in JSON Object"){
            val json = json {
                            root {
                                "age" to 12
                            }
                        }
            json.getValue("age") shouldBe 12
        }

        test("should add JSON Float Value in JSON Object"){
            val json = json {
                            root {
                                "x-coordinate" to 12.98
                            }
                        }
            json.getValue("x-coordinate") shouldBe 12.98
        }

        test("should add JSON Array Value in JSON Object"){
            val countries = arrayOf("India", "US", "UK")
            val json = json {
                            root {
                                "countries" to countries
                            }
                        }
            json.getValue("countries") shouldBe countries
        }

        test("should add JSON Boolean Value in JSON Object"){
            val json = json {
                            root {
                                "active" to true
                            }
                        }
            json.getValue("active") shouldBe true
        }

        test("should return String representation of JSON"){
            val expectedJson = """
                                    {
                                     "description" : "sample"
                                     "active" : true
                                     "countries" : [India, US, UK]
                                     "age" : 12
                                     }
                                """
            val json = json {
                            root {
                                "description" to "sample"
                                "active"      to true
                                "countries"   to arrayOf("India", "US", "UK")
                                "age"         to 12
                            }
                        }

            json.render() shouldBe expectedJson.trimIndent()
        }

        test("should add nested JSON in JSON Object"){
            val json = json {
                            root {
                                "description" to "sample"
                                "employee" to obj {
                                    "name" to "ABC"
                                }
                            }
            }

            json.getValue("description") shouldBe "sample"
        }

        test("should return String representation of JSON with nested Object"){
            val expectedJson = """
                                    {
                                     "description" : "sample"
                                     "active" : true
                                     "countries" : [India, US, UK]
                                     "age" : 12
                                     "employee" : {
                                      "name" : "John"
                                      "department" : "HR"
                                      "title" : "manager"
                                      "address" : {
                                       "city" : "NYC"
                                       "country" : "NY"
                                       }
                                      }
                                     }
                                """
            val json = json {
                            root {
                                "description" to "sample"
                                "active"      to true
                                "countries"   to arrayOf("India", "US", "UK")
                                "age"         to 12
                                "employee"    to  obj {
                                    "name"          to "John"
                                    "department"    to "HR"
                                    "title"         to "manager"
                                    "address"       to obj {
                                        "city"      to "NYC"
                                        "country"   to "NY"
                                    }
                                }
                            }
                        }

            json.render() shouldBe expectedJson.trimIndent()
        }
    }

}