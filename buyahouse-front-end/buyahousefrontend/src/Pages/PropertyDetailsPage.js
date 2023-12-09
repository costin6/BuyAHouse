import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import PropertyAPI from "../APIs/PropertyAPI";


function PropertyDetailsPage() {

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

    const TrueFalse = (bool) => {
        if (bool === true) {
            return "Yes"
        }
        else {
            return "No"
        }
    }

    const HouseApartment = (propertyType) => {
        if (propertyType === 2) {
            return "Apartment"
        }
        else {
            return "House"
        }
    }

    const PropertyDetails = () => {
        if (!property) {
            return <>Loading...</>
        }
        let mapURL = "https://www.google.com/maps/embed/v1/place?key=AIzaSyDBKb6VJZwochcOaLjii5JVQwd-Qa-Qsc0&q=" + property.address
        return (
            document.title = property.name,
            <div className="container-property-details">
                <div className="main-details">
                    <div className="details-photo-div">
                        <a target="_blank" href={property.photoLink}>
                            <img className="details-photo" src={property.photoLink} />
                        </a>
                    </div>
                    <div className="important-details">
                        <p className="property-name"> {property.name} </p>
                        <p className="details-price"> €{property.price} </p>
                        <p className="details-description"> {property.description} </p>
                        <p className="owner-details">Posted by: <b> {property.user.firstName} {property.user.lastName} ({property.user.phoneNumber}) </b> </p>
                        <Link to={`/chat/${property.id}`} className="chat-button">
                            Chat about this property
                        </Link>
                    </div>
                </div>
                <div className="small-details">
                    <div className="left">
                        <p className="detail">Area: <b> {property.area} m² </b> </p>
                        <p className="detail">Rooms: <b> {property.rooms} </b> </p>
                        <p className="detail">Parking included: <b> {TrueFalse(property.parkingIncluded)} </b> </p>
                        <p className="detail">Furnished: <b> {TrueFalse(property.furnished)} </b> </p>
                    </div>
                    <div className="right">
                        <p className="detail">Maximum occupancy: <b> {property.maxOccupancy} </b> </p>
                        <p className="detail">Outside area: <b> {TrueFalse(property.outsideArea)} </b> </p>
                        <p className="detail">Propetry type: <b> {HouseApartment(property.propertyTypeId)} </b> </p>
                        <p className="detail">Construction year: <b> {property.constructionYear} </b> </p>
                    </div>
                </div>
                <div className="location-details">
                    <p className="owner-details">Adress: <b> {property.address}, {property.city} </b> </p>
                    <iframe className="map" src={mapURL}></iframe>
                </div>
            </div>

        );
    }

    return (
        <PropertyDetails />
    );
}

export default PropertyDetailsPage;