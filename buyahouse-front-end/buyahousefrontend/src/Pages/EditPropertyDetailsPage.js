import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import PropertyAPI from "../APIs/PropertyAPI";
import EditPropertyDetails from "../Components/EditPropertyDetails";

const EditPropertyDetailsPage = () => {
    const { id } = useParams();
    const [property, setProperty] = useState();

    const getPropertyDetails = () => {
        PropertyAPI.getPropertyDetails(id)
            .then(propertyDetails => setProperty(propertyDetails))
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getPropertyDetails();
    }, []);

    const EditPropertyDetailsMethod = () => {
        if (!property) {
            return <>Loading...</>
        }
        const updateProperty = (name, description, area, rooms, price, city, parkingIncluded, furnished, maxOccupancy, outsideArea, propertyTypeId, address, constructionYear, photoLink) => {
            const request = {
                "id": property.id,
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
                "photoLink": photoLink
            };

            PropertyAPI.updateProperty(id, request)
                .catch(error => {
                    if (error.response?.data === "INVALID_PROPERTY") {
                        alert("Property ID invalid.");
                    }
                    else {
                        alert("Something went wrong");
                    }
                })
        }

        return (
            
            <EditPropertyDetails updateProperty={updateProperty} property={property} />
        );
    }

    return (
        document.title = "Edit property details",
        <EditPropertyDetailsMethod/>
    )
}
export default EditPropertyDetailsPage;