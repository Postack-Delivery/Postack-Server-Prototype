function onSubmitVariants() {
    window.alert("Hello World")
}

window.addEventListener('onCategoryChanged', (event) => {
    const selector = document.getElementById('SCSelector');
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
            for (var i = 0; i < groceriesData.length; i++) {
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
            for (var i = 0; i < bodyData.length; i++) {
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
            for (var i = 0; i < beveragesData.length; i++) {
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
            for (var i = 0; i < cleaningData.length; i++) {
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

window.dispatchEvent(new CustomEvent("onCategoryChanged", {detail: {category: "groceries"}}));