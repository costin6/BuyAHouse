import React from "react"
import AccountPropertyItem from "./AccountPropertyItem";

function AccountPropertyList(props) {
    return (
        <div className="products-overview">
            {props.propertyItems.map(propertyItem => (
                <AccountPropertyItem key={propertyItem?.propertyId} propertyItem={propertyItem} />
            ))}
        </div>
    )
}

export default AccountPropertyList;