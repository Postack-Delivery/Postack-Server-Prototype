package com.postack.routes

import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import kotlinx.css.body
import kotlinx.css.link
import kotlinx.css.script
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import org.litote.kmongo.document
import org.w3c.dom.html.HTMLInputElement
import org.w3c.dom.html.HTMLSelectElement

fun onClicked() {
    println("****************************************")
}
fun Route.productUploadRoutes() {


    route("/products/upload") {
        get {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    div {
                        h1 {
                            +"UPLOAD PRODUCT"
                        }
                        form(
                            action = "/api/v1/products",
                            encType = FormEncType.multipartFormData,
                            method = FormMethod.post
                        ) {
                            div(classes = "hContainer") {
                                p { +"Product Name: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_NAME
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Price: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_PRICE
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Description: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_DESCRIPTION
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Weight: " }
                                input {
                                    type = InputType.number
                                    name = C.PRODUCT_WEIGHT
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product UnitMeasure: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_UNIT_MEASURE
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Quantity: " }
                                input {
                                    type = InputType.number
                                    name = C.PRODUCT_QUANTITY
                                }
                            }
                                div {
                                    id = "SSContainer"
                                    p { +"Product Supplier: " }
                                    select {
                                        name = C.PRODUCT_SUPPLIER
                                        option {
                                            value = "town"
                                            +"Town-Center"
                                        }
                                        option {
                                            value = "soweto"
                                            +"Soweto"
                                        }

                                    }


                                    p { +"Product Category: " }
                                    select {
                                        id = "CSelector"
                                        name = C.PRODUCT_CATEGORY
                                        onChange = "window.dispatchEvent(new CustomEvent(\"onCategoryChanged\",{ detail: { category: document.getElementById(\"CSelector\").value } }));"
                                        option {
                                            value = "groceries"
                                            +"Groceries"
                                        }
                                        option {
                                            value = "body"
                                            +"Body & Bath"
                                        }
                                        option {
                                            value = "beverages"
                                            +"Beverages"
                                        }
                                        option {
                                            value = "cleaning"
                                            +"Clean Supplies"
                                        }
                                    }


                                }
                            div {
                                p {
                                    +"Product Sub-Category: "
                                }

                                select {
                                    id = "SCSelector"
                                    name = C.PRODUCT_SUB_CATEGORY

                                }
                            }
                            div {
                                p { +"Product Image: " }
                                input {
                                    type = InputType.file
                                    name = C.PRODUCT_IMAGE
                                }
                            }
                            submitInput { value = "Submit" }
                        }
                    }
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw("""
                                window.addEventListener('onCategoryChanged', (event) => {
                                    var selector = document.getElementById('SCSelector');
                                    while (selector.options.length > 0) {
                                      selector.remove(0);
                                    }
                                    switch (event.detail["category"]) {
                                        case 'groceries':
                                           const groceriesData = [{
                                                id: 3,
                                                name: "Breakfast",
                                                value: "breakfast"
                                           }, {
                                                id: 8,
                                                name: "Spices & Seasoning",
                                                value: "spices"
                                           }, {
                                               id: 10,
                                               name: "Treats & Snacks",
                                               value: "treats"
                                           },];  
                                           for(var i = 0; i < groceriesData.length; i++) {
                                              var option = document.createElement("option");
                                              option.text = groceriesData[i]["name"];
                                              option.value = groceriesData[i]["value"];
                                              selector.add(option);
                                           }
                                           break;
                                        case 'body':
                                           const bodyData = [{
                                                id: 3,
                                                name: "Body & Bath",
                                                value: "body"
                                           },];  
                                           for(var i = 0; i < bodyData.length; i++) {
                                              var option = document.createElement("option");
                                              option.text = bodyData[i]["name"];
                                              option.value = bodyData[i]["value"];
                                              selector.add(option);
                                           }
                                           break;
                                        case 'beverages':
                                           const beveragesData = [{
                                                id: 3,
                                                name: "Wine",
                                                value: "wine"
                                           }, {
                                                id: 8,
                                                name: "Whiskey & Spirits",
                                                value: "whiskey"
                                           }, {
                                               id: 10,
                                               name: "Soft Drinks",
                                               value: "soft-drinks"
                                           },];  
                                           for(var i = 0; i < beveragesData.length; i++) {
                                              var option = document.createElement("option");
                                              option.text = beveragesData[i]["name"];
                                              option.value = beveragesData[i]["value"];
                                              selector.add(option);
                                           }
                                           break;
                                        case 'cleaning':
                                          const cleaningData = [{
                                                id: 3,
                                                name: "Detergents",
                                                value: "detergents"
                                           }, {
                                                id: 8,
                                                name: "Sprays",
                                                value: "sprays"
                                           }, {
                                               id: 10,
                                               name: "Treats & Snacks",
                                               value: "treats"
                                           },];  
                                           for(var i = 0; i < cleaningData.length; i++) {
                                              var option = document.createElement("option");
                                              option.text = cleaningData[i]["name"];
                                              option.value = cleaningData[i]["value"];
                                              selector.add(option);
                                           }
                                           break;
                                        default:
                                          break;
                                    }
                                });
                                window.dispatchEvent(new CustomEvent("onCategoryChanged",{ detail: { category: "groceries" } }));                             
                            """)
                        }
                    }
                }
            }
        }
    }
}