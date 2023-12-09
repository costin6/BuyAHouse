import React, { useState } from "react"
import TokenManager from "../APIs/TokenManager";

function InputProperty(props) {
    const claims = TokenManager.getClaims();
    const [property, setProperty] = useState(props.property)
    const [name, setName] =
        useState(property.name);
    const [description, setDescription] =
        useState(property.description);
    const [area, setArea] =
        useState(property.area);
    const [rooms, setRooms] =
        useState(property.rooms);
    const [price, setPrice] =
        useState(property.price);
    const [city, setCity] =
        useState(property.city);
    const [parkingIncluded, setParkingIncluded] =
        useState(property.parkingIncluded);
    const [furnished, setFurnished] =
        useState(property.furnished);
    const [maxOccupancy, setMaxOccupancy] =
        useState(property.maxOccupancy);
    const [outsideArea, setOutsideArea] =
        useState(property.outsideArea);
    const [propertyTypeId, setPropertyTypeId] =
        useState(property.propertyTypeId);
    const [address, setAddress] =
        useState(property.address);
    const [constructionYear, setConstructionYear] =
        useState(property.constructionYear);
    const [photoLink, setPhotoLink] = 
        useState(property.photoLink);
    
    const handleSubmit = e => {
        e.preventDefault();
        props.updateProperty(name, description, area, rooms, price, city, parkingIncluded, furnished, maxOccupancy, outsideArea, propertyTypeId, address, constructionYear, photoLink);
        alert("Property details successfully updated!")
        if (claims?.roles?.includes('USER')) {
            window.location.href = '/my-account'
        }
        else{
            window.location.href = '/admin-properties'
        }
        
    }

    const Cancel = () => {
        if (claims?.roles?.includes('USER')) {
            window.location.href = '/my-account'
        }
        else{
            window.location.href = '/admin-properties'
        }
    }

    const parkingIncludedChanged = e => {
        var parkingCheck = document.getElementById("parkingIncluded");
        if (parkingCheck.checked === true) {
            setParkingIncluded(1)
        }
        else {
            setParkingIncluded(0)
        }
    }

    const furnishedChanged = e => {
        var furnishedCheck = document.getElementById("furnished");
        if (furnishedCheck.checked === true) {
            setFurnished(1)
        }
        else {
            setFurnished(0)
        }
    }


    const outsideAreaChanged = e => {
        var outsideAreaCheck = document.getElementById("outsideArea");
        if (outsideAreaCheck.checked === true) {
            setOutsideArea(1)
        }
        else {
            setOutsideArea(0)
        }
    }

    return (
        <div className="container create">
            <h1 className="page-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.01), #ffb366,  rgba(0,0,0,0.01))` }}>Create listing</h1>
            <form onSubmit={handleSubmit}>
                <div className="field">
                    <label className="field-label">
                        Name
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="text"
                        name="name"
                        id="name"
                        autoComplete="off"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div className="field">
                    <label className="field-label">
                        Description
                    </label>
                    <br />
                    <textarea
                        className="textarea"
                        name="description"
                        id="description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        rows="4"
                        cols="50">
                    </textarea>
                </div>
                <div className="field">
                    <label className="field-label">
                        Number of rooms
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="number"
                        name="rooms"
                        id="rooms"
                        value={rooms}
                        onChange={(e) => setRooms(e.target.value)}
                        min="1"
                        required />
                </div>
                <div className="field">
                    <label className="field-label">
                        Area (m²)
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="number"
                        name="area"
                        id="area"
                        value={area}
                        onChange={(e) => setArea(e.target.value)}
                        min="1"
                        required />
                </div>
                <div className="field">
                    <label className="field-label">
                        Price
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="number"
                        name="price"
                        id="price"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required />
                </div>
                <div className="field">
                    <label className="field-label">
                        City
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="text"
                        name="city"
                        id="city"
                        autoComplete="off"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                        required />
                </div>
                <div className="field">
                    <input
                        type="checkbox"
                        id="parkingIncluded"
                        value={parkingIncluded}
                        onChange={parkingIncludedChanged}
                        name="parkingIncluded" />
                    <label
                        for="parkingIncluded"> Parking included
                    </label>
                    <input
                        type="checkbox"
                        id="furnished"
                        value={furnished}
                        onChange={furnishedChanged}
                        name="furnished" />
                    <label
                        for="furnished"> Furnished
                    </label>
                </div>
                <div className="field">
                    <label className="field-label">
                        Maximum occupancy
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="number"
                        name="maxOccupancy"
                        id="maxOccupancy"
                        value={maxOccupancy}
                        onChange={(e) => setMaxOccupancy(e.target.value)}
                        required />
                </div>
                <div className="field">
                    <input
                        type="checkbox"
                        id="outsideArea"
                        value={outsideArea}
                        onChange={outsideAreaChanged}
                        name="outsideArea" />
                    <label
                        for="outsideArea"> Outside area
                    </label>
                </div>
                <div className="field">
                    <label className="field-label">
                        Property type
                    </label>
                    <br />
                    <input
                        type="radio"
                        id="propertyTypeId"
                        onChange={(e) => setPropertyTypeId(e.target.value)}
                        name="propertyTypeId"
                        value="2" />
                    <label
                        for="apartment"> Apartment
                    </label>
                    <input
                        type="radio"
                        id="propertyTypeId"
                        onChange={(e) => setPropertyTypeId(e.target.value)}
                        name="propertyTypeId"
                        value="1" />
                    <label
                        for="house"> House
                    </label>
                </div>
                <div className="field">
                    <label className="field-label">
                        Address
                    </label>
                    <br />
                    <textarea
                        className="textarea-address"
                        id="address"
                        name="address"
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                        rows="4"
                        cols="50"
                        required>
                    </textarea>
                </div>
                <div className="field">
                    <label className="field-label">
                        Build year
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="number"
                        name="constructionYear"
                        id="constructionYear"
                        value={constructionYear}
                        onChange={(e) => setConstructionYear(e.target.value)}
                        min="1900"
                        max="2023"
                        required />
                </div>
                <div className="field">
                    <label className="field-label">
                        Picture link
                    </label>
                    <br />
                    <label>
                        Please use <a href="https://imgur.com/" target="_blank">imgur.com</a>
                    </label>
                    <br />
                    <input
                        className="input-field"
                        type="text"
                        name="photoLink"
                        id="photoLink"
                        autoComplete="off"
                        value={photoLink}
                        onChange={(e) => setPhotoLink(e.target.value)}
                    />
                </div>
                <button className="createButton">Update listing</button>
            </form>
            <button onClick={Cancel} className="cancel-property">Cancel</button>
        </div>
    )
}

export default InputProperty;