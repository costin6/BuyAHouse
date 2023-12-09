import PropertyAPI from "../APIs/PropertyAPI";
import React, { useEffect, useState } from "react";
import InputProperty from '../Components/InputProperty.js';
import TokenManager from "../APIs/TokenManager";
import AuthAPI from "../APIs/AuthAPI";

function CreateListing() {
    const [user, setUser] = useState('');
    const claims = TokenManager.getClaims();

    const getUserDetails = () => {
        if (claims?.roles?.includes('USER') && claims?.userId) {
            AuthAPI.getLoggedUserDetails(claims.userId)
                .then(userDetails => setUser(userDetails))
                .catch(error => console.error(error));
        }
    }

    useEffect(() => {
        getUserDetails();
    }, []);

    const addProperty = (name, description, area, rooms, price, city, parkingIncluded, furnished, maxOccupancy, outsideArea, propertyTypeId, address, constructionYear, userCreate, photoLink) => {
        userCreate = user;
        const property = {
            "name": name,
            "description": description,
            "area": area,
            "rooms": rooms,
            "price": price,
            "city": city,
            "parkingIncluded": parkingIncluded,
            "furnished": furnished,
            "maxOccupancy": maxOccupancy,
            "outsideArea": outsideArea,
            "propertyTypeId": propertyTypeId,
            "address": address,
            "constructionYear": constructionYear,
            "user": userCreate,
            "photoLink": photoLink
        };
        PropertyAPI.createProperty(property)
            .then(propertyId => {
                if(propertyId !== null){
                    alert("Listing created!");
                    window.location.href='/my-account'
                }
            })
            .catch(error => {
                if (error.response?.status === 400
                    && error.response?.data === "PROPERTY_DUPLICATED") {
                    alert("Property item duplicated.");
                } else {
                    alert("Something went wrong. Please try again later.");
                }
            });
    }

    return (
        document.title = "Create listing",
        <InputProperty addProperty={addProperty} />
    )
}

export default CreateListing;