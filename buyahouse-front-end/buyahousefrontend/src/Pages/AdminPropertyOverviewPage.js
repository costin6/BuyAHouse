import PropertyAPI from "../APIs/PropertyAPI";
import React, { useEffect, useState } from "react";
import PropertyList from "../Components/PropertyList";
import AdminPropertyList from "../Components/AdminPropertyList";

function PropertyOverviewPage() {
  const [propertyItems, setPropertyItems] = useState([]);
  const [query, setQuery] = useState("");

  const getPropertyList = () => {
    PropertyAPI.getPropertyOverview()
      .then((properties) => setPropertyItems(properties))
      .catch((error) => console.error(error));
  };

  const getSearchedPropertyList = () => {
    PropertyAPI.getSearchedProperties(query)
      .then((properties) => setPropertyItems(properties))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    if (query === "") {
      getPropertyList();
    } else {
      getSearchedPropertyList();
    }
  }, [query]);

  const isSearchResultEmpty = propertyItems.length === 0;

  return (
    document.title = "Properties",
    <div className="container">
      <h1
        className="page-title"
        style={{
          backgroundImage:
            'linear-gradient(to right, rgba(0, 0, 0, 0.01), #ffb366, rgba(0, 0, 0, 0.01))',
        }}
      >
        Available properties
      </h1>
      <input
        type="search"
        className="search-bar"
        placeholder="Search.."
        onChange={(e) => setQuery(e.target.value)}
      />
      <div className="inner">
        {isSearchResultEmpty ? (
          <p>No results found.</p>
        ) : (
          <AdminPropertyList propertyItems={propertyItems} />
        )}
      </div>
    </div>
  );
}

export default PropertyOverviewPage;