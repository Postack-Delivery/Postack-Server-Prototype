package com.postack.routes.components

import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun DIV.sideBarTabs() {
    tag("SIDEBAR")
    div(classes = "col-sm-2 gray") {
        ul(classes = "nav flex-column") {
            id = "v-pills-tab"
            role = "tablist"
            attributes["aria-orientation"] = "vertical"
            li(classes = "nav-link") {
                h6(classes = "text-muted") {
                    strong {
                        +"Products"
                    }

                }
                ul {
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link active text-decoration-none ") {
                            id = "product-inventory-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#product-inventory"
                            attributes["aria-controls"] = "product-inventory"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-cubes") {}
                                +" Inventory"
                            }
                        }
                    }
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none text-gray") {
                            id = "product-suppliers-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#product-suppliers"
                            attributes["aria-controls"] = "product-suppliers"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-users") {}
                                +" Suppliers"
                            }
                        }
                    }
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none") {
                            id = "product-categories-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#product-categories"
                            attributes["aria-controls"] = "product-categories"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-server") {}
                                +" Categories"
                            }
                        }
                    }
                }
            }
            li(classes = "nav-link") {
                h6(classes = "text-muted") {
                    strong {
                        +"Orders"
                    }
                }
                ul {
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none") {
                            id = "order-completed-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#order-completed"
                            attributes["aria-controls"] = "order-completed"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-book") {}
                                +" Completed"
                            }
                        }
                    }
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none") {
                            id = "order-progress-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#order-progress"
                            attributes["aria-controls"] = "order-progress"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-bullseye") {}
                                +" Ongoing"
                            }
                        }
                    }
                }
            }
            li(classes = "nav-link") {
                h6(classes = "text-muted") {
                    strong {
                        +"Advertising"
                    }
                }
                ul {
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none") {
                            id = "advertising-create-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#advertising-create"
                            attributes["aria-controls"] = "advertising-create"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-audio-description") {}
                                +" Create"
                            }
                        }
                    }
                    li(classes = "nav-link") {
                        button(classes = "btn btn-link nav-link text-decoration-none") {
                            id = "advertising-ongoing-tab"
                            role = "tab"
                            attributes["type"] = "button"
                            attributes["data-bs-toggle"] = "tab"
                            attributes["data-bs-target"] = "#advertising-ongoing"
                            attributes["aria-controls"] = "advertising-ongoing"
                            attributes["aria-selected"] = "false"
                            span {
                                i(classes = "fa fa-bullseye") {}
                                +" Ongoing"
                            }
                        }
                    }
                }
            }
        }
    }
}