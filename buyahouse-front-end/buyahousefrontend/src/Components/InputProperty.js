import React, { useState } from "react"

function InputProperty(props) {

    const [name, setName] =
        useState("dummyTitle");
    const [description, setDescription] =
        useState("dummyDescription");
    const [area, setArea] =
        useState("dummyArea");
    const [rooms, setRooms] =
        useState("dummyRooms");
    const [price, setPrice] =
        useState("dummyPrice");
    const [city, setCity] =
        useState("dummyCity");
    const [parkingIncluded, setParkingIncluded] =
        useState(false);
    const [furnished, setFurnished] =
        useState(false);
    const [maxOccupancy, setMaxOccupancy] =
        useState("dummyMaxOccupancy");
    const [outsideArea, setOutsideArea] =
        useState(false);
    const [propertyTypeId, setPropertyTypeId] =
        useState("dummyPropertyTypeId");
    const [address, setAddress] =
        useState("dummyAddress");
    const [constructionYear, setConstructionYear] =
        useState("dummyConstructionYear");
    const [photoLink, setPhotoLink] =
        useState("dummyPhotoLink");
    const user = props.addProperty.user

    const handleSubmit = e => {
        e.preventDefault();
        props.addProperty(name, description, area, rooms, price, city, parkingIncluded, furnished, maxOccupancy, outsideArea, propertyTypeId, address, constructionYear, user, photoLink);
    }

    const nameChanged = e => {
        setName(e.target.value)
    }

    const descriptionChanged = e => {
        setDescription(e.target.value)
    }

    const areaChanged = e => {
        setArea(e.target.value)
    }

    const roomsChanged = e => {
        setRooms(e.target.value)
    }

    const priceChanged = e => {
        setPrice(e.target.value)
    }

    const cityChanged = e => {
        setCity(e.target.value)
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

    const maxOccupancyChanged = e => {
        setMaxOccupancy(e.target.value)
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

    const propertyTypeChanged = e => {
        setPropertyTypeId(e.target.value)
    }

    const addressChanged = e => {
        setAddress(e.target.value)
    }

    const constructionYearChanged = e => {
        setConstructionYear(e.target.value)
    }

    const photoLinkChanged = e => {
        setPhotoLink(e.target.value)
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
                        onChange={nameChanged}
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
                        onChange={descriptionChanged}
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
                        onChange={roomsChanged}
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
                        onChange={areaChanged}
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
                        onChange={priceChanged}
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
                        onChange={cityChanged}
                        required />
                </div>
                <div className="field">
                    <input
                        type="checkbox"
                        id="parkingIncluded"
                        onChange={parkingIncludedChanged}
                        name="parkingIncluded" />
                    <label
                        htmlFor="parkingIncluded"> Parking included
                    </label>
                    <input
                        type="checkbox"
                        id="furnished"
                        onChange={furnishedChanged}
                        name="furnished" />
                    <label
                        htmlFor="furnished"> Furnished
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
                        onChange={maxOccupancyChanged}
                        required />
                </div>
                <div className="field">
                    <input
                        type="checkbox"
                        id="outsideArea"
                        onChange={outsideAreaChanged}
                        name="outsideArea" />
                    <label
                        htmlFor="outsideArea"> Outside area
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
                        onChange={propertyTypeChanged}
                        name="propertyTypeId"
                        value="2" />
                    <label
                        htmlFor="apartment"> Apartment
                    </label>
                    <input
                        type="radio"
                        id="propertyTypeId"
                        onChange={propertyTypeChanged}
                        name="propertyTypeId"
                        value="1" />
                    <label
                        htmlFor="house"> House
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
                        onChange={addressChanged}
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
                        onChange={constructionYearChanged}
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
                        onChange={photoLinkChanged} />
                </div>
                <button className="createButton">Create listing</button>
            </form>
        </div>
    )
}

export default InputProperty;