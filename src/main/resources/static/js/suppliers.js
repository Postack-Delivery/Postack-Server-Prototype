
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
