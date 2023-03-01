const editProductModal = document.getElementById('editProductModal')

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

    window.sessionStorage.setItem("productQueuedForEdit", JSON.stringify(product))
}

function onVariantSelected(i, categories) {
    const product = JSON.parse(window.sessionStorage.getItem("productQueuedForEdit"));
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
    document.getElementById('add-product-variant-product-id').value = productId;
    console.log(`[onAddProductVariant]-${productId}`)
}
function onRemoveProductVariant(product) {
    console.log(`[onDeleteProductVariant]`)
    console.log(product)
    window.sessionStorage.setItem("productQueuedForRemoval", JSON.stringify(product));
    for (var i = 0; i < product.variants.length; i++) {
        console.log(product.variants[i].name);

        const editName = document.getElementById(`delete-variant-name${i + 1}`);
        editName.disabled = false;
        editName.hidden = false;
        editName.value = product.variants[i].name

        const deleteBtn = document.getElementById(`delete-variant-button${i + 1}`);
        deleteBtn.hidden = false;
        deleteBtn.disabled = false;


    }
    for (var i = product.variants.length; i < 12; i++) {
        const editName = document.getElementById(`delete-variant-name${i + 1}`);
        const deleteBtn = document.getElementById(`delete-variant-button${i + 1}`);
        editName.disabled = true;
        editName.hidden = true;
        deleteBtn.hidden = true;
        deleteBtn.disabled = true;

    }

}

function onDeleteProductVariant(index) {
    const product = JSON.parse(window.sessionStorage.getItem("productQueuedForRemoval"))
    console.log(`[onDeleteVariant] - ${product.variants[index].name}`);
    onDeleteItem(product.variants[index].id, "variant");
    document.getElementById("delete-variant-product-id").value = `${product.id}`;
}
editProductModal.addEventListener('hidden.bs.modal', event => {
    document.getElementById('edit-product-variant-name').value = "";
    document.getElementById('edit-product-variant-price').value = "";
    document.getElementById('edit-product-variant-weight').value = "";
    document.getElementById('edit-product-variant-quantity').value = "";
    document.getElementById('edit-product-variant-unit').value = "";
    document.getElementById('edit-product-variant-description').value = "";
});