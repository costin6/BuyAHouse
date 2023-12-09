import React from "react"
import { Link } from "react-router-dom";
import PropertyAPI from "../APIs/PropertyAPI";
import { useState } from "react";
import ConfirmationDialog from "./ConfirmationDialog";
import TokenManager from "../APIs/TokenManager";


function AccountPropertyItem(props) {

    const claims = TokenManager.getClaims();

    const [dialog, setDialog] = useState({
        message: "",
        isLoading: false,
        nameProduct: ""
    });
    const handleDialog = (message, isLoading) => {
        setDialog({
            message,
            isLoading
        });
    };

    const handleDelete = () => {
        handleDialog("Are you sure?", true);
    };

    const areUSureDelete = (choose) => {
        if (choose) {
            handleDialog("", false);
            if (claims?.roles?.includes('ADMIN') && claims?.userId) {
                PropertyAPI.deleteProperty(props.propertyItem.id)
                    .catch(error => console.error(error));
            }
            alert("Property successfully deleted!")
            window.location.href = '/admin-properties'
        } else {
            handleDialog("", false);
        }
    };

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
                <Link to={`/edit-property/${props.propertyItem.id}`} className="account-details-button">
                    Edit property
                </Link>
                <br />
                <button onClick={() => handleDelete(1)} className="account-delete-button">
                    Delete property
                </button>
                {dialog.isLoading && (
                    <ConfirmationDialog
                        nameProduct={dialog.nameProduct}
                        onDialog={areUSureDelete}
                        message={dialog.message}
                    />
                )}
            </div>
        );
    }

    return (
        <div className="profile-productCard">
            <ProductCard />
        </div>
    )
}

export default AccountPropertyItem;