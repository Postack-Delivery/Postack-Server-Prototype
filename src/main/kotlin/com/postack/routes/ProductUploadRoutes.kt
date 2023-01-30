package com.postack.routes

import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import kotlinx.css.body
import kotlinx.css.div
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
                    meta { charset = "utf-8" }
                    meta {
                        name = "viewport"
                        content = "width=device-width, initial-scale=1"
                    }
                    unsafe {
                        +"""<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">"""
                    }
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")

                }
                body {
                    nav(classes = "navbar bg-body-tertiary") {
                        div(classes = "container-fluid") {
                            span(classes = "navbar-brand mb-0 h1") { +"Postack Dashboard" }
                        }
                    }

                    div(classes = "row") {
                        div(classes = "col-sm-2") {

                            ul(classes = "nav flex-column") {
                                id = "v-pills-tab"
                                role = "tablist"
                                attributes["aria-orientation"] = "vertical"
                                li(classes = "nav-link active") {
                                    h6 { +"Products" }
                                    ul {
                                        li(classes = "nav-link") {
                                            a {
                                                id = "product-upload-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-upload-tab"
                                                attributes["aria-controls"] = "product-upload-tab"
                                                attributes["aria-selected"] = "true"
                                                +"Upload"
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a {
                                                id = "v-pills-home-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-home"
                                                attributes["aria-controls"] = "v-pills-home"
                                                attributes["aria-selected"] = "true"
                                                +"Inventory"
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a {
                                                id = "v-pills-home-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-home"
                                                attributes["aria-controls"] = "v-pills-home"
                                                attributes["aria-selected"] = "true"
                                                +"Suppliers"
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a {
                                                id = "v-pills-home-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-home"
                                                attributes["aria-controls"] = "v-pills-home"
                                                attributes["aria-selected"] = "true"
                                                +"Categories"
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link active") {
                                    h6 { +"Orders" }
                                    ul {
                                        li(classes = "nav-link") {
                                            a {
                                                id = "v-pills-profile-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-profile"
                                                attributes["aria-controls"] = "v-pills-profile"
                                                attributes["aria-selected"] = "false"
                                                +"Completed"
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a {
                                                id = "orders-progress"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-profile"
                                                attributes["aria-controls"] = "v-pills-profile"
                                                attributes["aria-selected"] = "false"
                                                +"Ongoing"
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link active") {
                                    h6 { +"Advertising" }
                                    ul {
                                        li(classes = "nav-link") {
                                            a {
                                                id = "v-pills-profile-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-profile"
                                                attributes["aria-controls"] = "v-pills-profile"
                                                attributes["aria-selected"] = "false"
                                                +"Create"
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a {
                                                id = "orders-progress"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#v-pills-profile"
                                                attributes["aria-controls"] = "v-pills-profile"
                                                attributes["aria-selected"] = "false"
                                                +"Ongoing"
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        div(classes = "col-sm-8") {

                            div(classes = "tab-content") {
                                id = "v-pills-tabContent"
                                div(classes = "tab-pane fade show active") {
                                    id = "product-upload-tab"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-upload-tab"
                                    tabIndex = "0"
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
                                                    onChange =
                                                        "window.dispatchEvent(new CustomEvent(\"onCategoryChanged\",{ detail: { category: document.getElementById(\"CSelector\").value } }));"
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
                                }
                                div(classes = "tab-pane fade") {
                                    id = "v-pills-profile"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "v-pills-profile-tab"
                                    tabIndex = "0"
                                    +"Profile"
                                }
                            }
                        }
                    }



                    unsafe {
                        +"""
                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
                        """.trimIndent()
                    }
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw(
                                """
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
                            """
                            )
                        }
                    }
                }
            }
        }
    }
}