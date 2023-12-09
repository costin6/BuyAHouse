import React from "react"
import PropertyItem from "./PropertyItem";

function PropertyList(props) {
    return (
        <div className="products-overview">
            {props.propertyItems.map(propertyItem => (
                <PropertyItem key={propertyItem?.propertyId} propertyItem={propertyItem}/>
            ))}
        </div>
    )
}

export default PropertyList;