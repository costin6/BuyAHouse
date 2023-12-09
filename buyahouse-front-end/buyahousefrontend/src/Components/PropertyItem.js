import React from "react"
import { Link } from "react-router-dom";

function PropertyItem(props) {
    const ProductCard = () => {
        if (!props.propertyItem) {
            return <>Loading...</>
        }
        return (    
            <div>
                <Link to={`/properties/${props.propertyItem.id}`} className="details-button">
                    <img className="product-img" src={props.propertyItem.photoLink} />
                    <h3>{props.propertyItem.name}</h3>
                    <h3 className="price"> €{props.propertyItem.price} </h3>
                    <h5>{props.propertyItem.description}</h5>
                </Link>
            </div>
        );
    }

    return (
        <div className="productCard">
            <ProductCard />
        </div>
    )
}

export default PropertyItem;