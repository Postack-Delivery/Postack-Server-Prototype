
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
    const tabTrigger = new bootstrap.Tab(triggerEl);

    triggerEl.addEventListener('click', event => {
        event.preventDefault();
        tabTrigger.show();
    });
})


function onSupplierEdit(supplier) {
    console.log(`[TEST] - onUpdate ${supplier.name}`);
    const name = document.getElementById('edit-supplier-name');
    name.value = supplier.name;
    const location = document.getElementById('edit-supplier-location');
    location.value = supplier.location;
    const city = document.getElementById('edit-supplier-city');
    city.value = supplier.city;
    const id = document.getElementById('edit-supplier-id');
    id.value = supplier.id;
}

function onDeleteItem(itemId) {
    console.log(`[onDeleteItem] - ${itemId}`);
    const id = document.getElementById('item-id');
    id.value = itemId;
}

function onAddSubCategory(categoryId) {
    console.log(`[onAddSubCategory] - ${categoryId}`);
     const id = document.getElementById('category-id');
     id.value = categoryId;
}

function onEditCategory(category) {
    console.log(category.data);
    window.localStorage.setItem("categories", JSON.stringify(category))
}

const editCategoryModal = document.getElementById('editCategory')
editCategoryModal.addEventListener('shown.bs.modal', event => {
    const category = JSON.parse(window.localStorage.getItem("categories"))
    const categoryId = document.getElementById(`edit-category-id`);
    document.getElementById('item-id').value = category.id;
    categoryId.value = category.id
    const categoryName = document.getElementById(`edit-category-name`);
    categoryName.value = category.label

    console.log(category.data);
    for(var i = 0; i < category.data.length; i++) {
        console.log(category.data[i].name);
        const editName = document.getElementById(`edit-subcategory-name${i + 1}`);
        editName.disabled = false;
        editName.hidden = false;
        editName.value = category.data[i].name;
        editName.name = `subcategory-name${i + 1}`
        if (i > 0) {
            const deleteBtn = document.getElementById(`delete-subcategory-name${i + 1}`);
            deleteBtn.hidden = false;
            deleteBtn.disabled = false;
        }

    }
    for (var i = category.data.length; i < 10; i++) {
        const editName = document.getElementById(`edit-subcategory-name${i + 1}`);
        const deleteBtn = document.getElementById(`delete-subcategory-name${i + 1}`);
        editName.disabled = true;
        editName.hidden = true;
        deleteBtn.hidden = true;
        deleteBtn.disabled = true;

    }

})


function onDeleteCategory(index) {
    const category = JSON.parse(window.localStorage.getItem("categories"))
    console.log(`[onDeleteItem] - ${category.data[index - 1].name}`);
    const id = document.getElementById('item-id');
    id.value = category.data[index - 1].id;
    const categoryId = document.getElementById('parent-id');
    categoryId.value = category.id
}