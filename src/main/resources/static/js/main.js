

window.addEventListener('onCategoryChanged', (event) => {
    const selector = document.getElementById(event.detail["id"]);
    while (selector.options.length > 0) {
        selector.remove(0);
    }
    switch (event.detail["category"]) {
        case 'groceries':
            const resGroceries = JSON.stringify(event.detail["data"]);
            console.log("[RES]" + resGroceries);
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
            let resBody = JSON.stringify(event.detail["data"]);
            console.log("[RES]" + resBody);
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
            let resBeverages = JSON.stringify(event.detail["data"]);
            console.log("[RES]" + resBeverages);
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
            let resCleaning = JSON.stringify(event.detail["data"]);
            console.log("[RES]" + resCleaning);
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
    console.log(`[onSupplierEdit] - ${supplier.name}`);
    const name = document.getElementById('edit-supplier-name');
    name.value = supplier.name;
    const location = document.getElementById('edit-supplier-location');
    location.value = supplier.location;
    const city = document.getElementById('edit-supplier-city');
    city.value = supplier.city;
    const id = document.getElementById('edit-supplier-id');
    id.value = supplier.id;
}

function onEditProduct(product) {
    console.log(product);
    const id = document.getElementById('edit-product-id');
    id.value = product.id;
    const name = document.getElementById('edit-product-name');
    name.value = product.name;
    const selectProductVariant = document.getElementById('edit-product-variant-selector');
    while (selectProductVariant.options.length > 0) {
        selectProductVariant.remove(0);
    }

    var option = document.createElement("option");
    option.text = "Select a variant";
    option.value = "";
    option.hidden = true;
    option.hidden = true;
    selectProductVariant.add(option);
    selectProductVariant.selectedIndex = 0;
    product.variants.forEach((variant) => {
        var option = document.createElement("option");
        option.text = variant.name;
        option.value = variant.name.split(' ')[0];
        selectProductVariant.add(option);
    });

    window.localStorage.setItem("productQueuedForEdit", JSON.stringify(product))
}

function onVariantSelected(i, categories) {
    const product = JSON.parse(window.localStorage.getItem("productQueuedForEdit"));
    const productVariant = product.variants[i - 1];
    console.log(productVariant);
    const name = document.getElementById('edit-product-variant-name');
    name.value = productVariant.name;
    const price = document.getElementById('edit-product-variant-price');
    price.value = productVariant.price;
    const weight = document.getElementById('edit-product-variant-weight');
    weight.value = productVariant.weight;
    const quantity = document.getElementById('edit-product-variant-quantity');
    quantity.value = productVariant.quantity;
    const unit = document.getElementById('edit-product-variant-unit');
    unit.value = productVariant.unitMeasure;
    const description = document.getElementById('edit-product-variant-description');
    description.value = productVariant.description;
    const selectProductSupplier = document.getElementById('edit-product-supplier-selector');
    for (var i = 0; i < selectProductSupplier.options.length; i++) {
        if (selectProductSupplier.options[i].value === product.supplier) {
            selectProductSupplier.selectedIndex = i
        }
    }
    const selectProductCategory = document.getElementById('edit-product-category-selector');
    for (var i = 0; i < selectProductCategory.options.length; i++) {
        if (selectProductCategory.options[i].value === product.category) {
            selectProductCategory.selectedIndex = i
        }
    }
    window.dispatchEvent(new CustomEvent(
        "onCategoryChanged", { detail: {
                category: product.category,
                data: categories,
                id: "edit-product-subcategory-selector"
            }
        }
    ));
}

function onAddProductVariant(productId) {
    console.log(`[onAddProductVariant]-${productId}`)
}

function onAddSubCategory(categoryId) {
    console.log(`[onAddSubCategory] - ${categoryId}`);
    const id = document.getElementById('category-id');
    id.value = categoryId;
}

function onEditCategory(category) {
    console.log(category);
    window.localStorage.setItem("categories", JSON.stringify(category))
}

const editCategoryModal = document.getElementById('editCategory')
editCategoryModal.addEventListener('shown.bs.modal', event => {
    const category = JSON.parse(window.localStorage.getItem("categories"))
    const categoryId = document.getElementById(`edit-category-id`);
    categoryId.value = category.id
    const categoryName = document.getElementById(`edit-category-name`);
    categoryName.value = category.label

    console.log(category.data);
    for (var i = 0; i < category.data.length; i++) {
        console.log(category.data[i].name);

            const editName = document.getElementById(`edit-subcategory-name${i + 1}`);
            editName.disabled = false;
            editName.hidden = false;
            editName.value = category.data[i].name

            const deleteBtn = document.getElementById(`delete-subcategory-name${i + 1}`);
            deleteBtn.hidden = false;
            deleteBtn.disabled = false;


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


function onDeleteSubcategory(index) {
    const category = JSON.parse(window.localStorage.getItem("categories"))
    console.log(`[onDeleteSubcategory] - ${category.data[index - 1].name}`);
    onDeleteItem(category.data[index - 1].id, "subcategory");
    document.getElementById("delete-subcategory-category-id").value = `${category.id}`;
}



function onDeleteItem(itemId, name) {
    console.log(`[onDeleteItem] - ${itemId}`);
    const id = document.getElementById(`delete-${name}-id`);
    id.value = itemId;
}