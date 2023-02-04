
function onSubmitVariants() {
    const result = parseObjectString(arguments[1]);
    window.alert("Hello " + result);
    const arrayOfVariants = JSON.parse(result)
    console.log(arrayOfVariants[0].id);
}
function parseObjectString() {
    return JSON.parse(JSON.stringify(arguments[0].replaceAll('-', '"')))
}
window.addEventListener('onCategoryChanged', (event) => {
    const selector = document.getElementById('SCSelector');
    while (selector.options.length > 0) {
        selector.remove(0);
    }
    switch (event.detail["category"]) {
        case 'groceries':
            const resGroceries = JSON.parse(JSON.stringify(event.detail["data"].replaceAll('-', '"')));
            console.log("[RES]" +resGroceries);
            const groceryCategories = JSON.parse(resGroceries).filter(category => category.category === "groceries")
            console.log(groceryCategories);

            for (var i = 0; i < groceryCategories.length; i++) {
                var option = document.createElement("option");
                option.text = groceryCategories[i].name;
                option.value = groceryCategories[i].name.split(' ')[0];
                selector.add(option);
            }
            break;
        case 'body':
            let resBody = JSON.parse(JSON.stringify(event.detail["data"].replaceAll('-', '"')));
            console.log("[RES]" +resBody);
            const bodyCategories = JSON.parse(resBody).filter(category => category.category === "body")
            console.log(bodyCategories);

            for (var i = 0; i < bodyCategories.length; i++) {
                var option = document.createElement("option");
                option.text = bodyCategories[i].name;
                option.value = bodyCategories[i].name.split(' ')[0];
                selector.add(option);
            }
            break;
        case 'beverages':
            let resBeverages = JSON.parse(JSON.stringify(event.detail["data"].replaceAll('-', '"')));
            console.log("[RES]" +resBeverages);
            const beverageCategories = JSON.parse(resBeverages).filter(category => category.category === "beverages")
            console.log(beverageCategories);

            for (var i = 0; i < beverageCategories.length; i++) {
                var option = document.createElement("option");
                option.text = beverageCategories[i].name;
                option.value = beverageCategories[i].name.split(' ')[0];
                selector.add(option);
            }
            break;
        case 'cleaning':
            let resCleaning = JSON.parse(JSON.stringify(event.detail["data"].replaceAll('-', '"')));
            console.log("[RES]" +resCleaning);
            const cleaningCategories = JSON.parse(resCleaning).filter(category => category.category === "cleaning")
            console.log(cleaningCategories);

            for (var i = 0; i < cleaningCategories.length; i++) {
                var option = document.createElement("option");
                option.text = cleaningCategories[i].name;
                option.value = cleaningCategories[i].name.split(' ')[0];
                selector.add(option);
            }
            break;
        default:
            break;
    }
});

const triggerTabList = document.querySelectorAll('#v-pills-tab button')
triggerTabList.forEach(triggerEl => {
    const tabTrigger = new bootstrap.Tab(triggerEl)

    triggerEl.addEventListener('click', event => {
        event.preventDefault()
        tabTrigger.show()
    })
})

const triggerEl = document.querySelector('#v-pills-tab button[data-bs-target="#product-inventory"]')
bootstrap.Tab.getInstance(triggerEl).show()


function onSupplierEdit(supplier) {
    console.log(`[TEST] - onUpdate ${supplier.name}`)
    const name = document.getElementById('edit-supplier-name')
    name.value = supplier.name
    const location = document.getElementById('edit-supplier-location')
    location.value = supplier.location
    const city = document.getElementById('edit-supplier-city')
    city.value = supplier.city
}