import React from "react"
import AdminPropertyItem from "./AdminPropertyItem";

function AdminPropertyList(props) {
    return (
        <div className="products-overview">
            {props.propertyItems.map(propertyItem => (
                <AdminPropertyItem key={propertyItem?.propertyId} propertyItem={propertyItem} />
            ))}
        </div>
    )
}

export default AdminPropertyList;