import axios from "axios";
import TokenManager from "./TokenManager";

const PROPERTY_BASE_URL = "http://localhost:8080/properties";

const PropertyAPI = {
    getPropertyOverview: () => axios.get(PROPERTY_BASE_URL)
        .then(repsonse => repsonse.data.properties),
    getPropertyDetails: (id) => axios.get(`http://localhost:8080/properties/${id}`)
        .then(repsonse => repsonse.data.propertyDetails),
    getSearchedProperties: (query) => axios.get(`http://localhost:8080/properties/search/${query}`)
        .then(repsonse => repsonse.data.properties),
    createProperty: (property) => axios.post(PROPERTY_BASE_URL, property,
        {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        .then(repsonse => repsonse.data.id),
    getPropertiesByUserId: () => axios.get(`http://localhost:8080/properties/statistics`)
        .then(repsonse => repsonse.data.propertyByUserIds),
    getPropertyOverviewByUser: (id) => axios.get(`http://localhost:8080/properties/myproperties/${id}`)
        .then(repsonse => repsonse.data.properties),
    updateProperty: (id, request) => axios.put(`http://localhost:8080/properties/${id}`, request,
        {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        }),
    deleteProperty: (id) => axios.delete(`http://localhost:8080/properties/${id}`,
        {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
};

export default PropertyAPI;