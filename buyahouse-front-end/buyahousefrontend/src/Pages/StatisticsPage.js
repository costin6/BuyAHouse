import React from "react";
import { Chart } from "react-google-charts";
import PropertyAPI from "../APIs/PropertyAPI";
import { useState, useEffect } from "react";

const StatisticsPage = () => {
    const [properties, setProperties] = useState([]);
    const data = [
        ["User id", "Properties"],
    ];

    const options = {
        title: "Properties by user",
    };

    const getPropertiesByUserIds = () => {
        PropertyAPI.getPropertiesByUserId()
            .then(propertyByUserIds => setProperties(propertyByUserIds))
            .catch((error) => console.error(error));
    }

    let arrayLength = properties.length;
    
    const ShowPropertiesByUserId = () => {
        if (arrayLength === 0) {
            return <>Loading...</>
        }
        else {
            while (arrayLength > 0) {
                const array = [String(properties[arrayLength - 1].username), properties[arrayLength - 1].countProperties]
                arrayLength = arrayLength - 1;
                data.push(array)
            }
        }
        return (
            <Chart
                chartType="PieChart"
                data={data}
                options={options}
                width={"100%"}
                height={"400px"}
            />
        );
    }

    useEffect(() => {
        getPropertiesByUserIds();
    }, []);

    return (
        document.title = "Statistics",
        <ShowPropertiesByUserId />
    )
}

export default StatisticsPage;
