import "./App.css";
import React, { useEffect, useState } from "react";

function App() {
  const [countries, setCountries] = useState([]);
  const [country, setCountry] = useState();
  const [tab, setTab] = useState("history");
  const [state, setState] = useState({ status: "idle" });

  useEffect(() => {
    const getCountries = async () => {
      const options = {
        method: "GET",
        headers: {
          "X-RapidAPI-Host": "covid-193.p.rapidapi.com",
          "X-RapidAPI-Key":
            "e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22",
        },
      };

      try {
        const res = await fetch("http://localhost:8080/api/countries", options);
        const response = await res.json();
        const countries = response.response.map((country) => ({
          value: country.toLowerCase(),
          label: country,
        }));
        setCountries(countries);
      } catch (e) {
        console.log(e);
      }
    };

    getCountries();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const options = {
      method: "GET",
      headers: {
        "X-RapidAPI-Host": "covid-193.p.rapidapi.com",
        "X-RapidAPI-Key": "e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22",
      },
    };

    let url;
    const country = e.currentTarget.country?.value;

    console.log(country);

    if (tab === "history") {
      const date = e.currentTarget.date?.value;

      console.log(date);

      if (date) {
        url = `http://localhost:8080/api/history?country=${country}&day=${date}`;
      } else {
        url = `http://localhost:8080/api/history?country=${country}`;
      }
    } else if (tab === "statistics") {
      if (country !== "null") {
        url = `http://localhost:8080/api/statistics?country=${country}`;
      } else {
        url = "http://localhost:8080/api/statistics";
      }
    }
    const resp = await fetch(url, options);
    const response = await resp.json();

    setState({ status: "success", data: response.response });
  };

  useEffect(() => {
    setCountry("");
  }, [tab]);

  return (
    <div className="App">
      <h1>Covid Incidence Statistics</h1>
      <div className="body">
        <div className="tabs">
          <button
            id="history"
            className={tab === "history" ? "active" : undefined}
            onClick={() => {
              setState({ status: "idle" });
              setTab("history");
            }}
          >
            History
          </button>
          <button
            id="statistics"
            className={tab === "statistics" ? "active" : undefined}
            onClick={() => {
              setState({ status: "idle" });
              setTab("statistics");
            }}
          >
            Statistics
          </button>
        </div>

        {tab === "history" && (
          <form onSubmit={handleSubmit}>
            <label htmlFor="country">Country</label>
            <select name="country" id="country">
              <option disabled selected value="null">
                Select a country
              </option>
              {countries.map((option) => (
                <option id={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
            <label htmlFor="date">Date (optional)</label>
            <input type="date" id="date" name="history-date"></input>
            <button type="submit" id="search">
              Search
            </button>
          </form>
        )}
        {tab === "statistics" && (
          <form onSubmit={handleSubmit}>
            <label htmlFor="country">Country</label>
            <select name="country" id="country">
              <option disabled selected value="null">
                Select a country
              </option>
              {countries.map((option) => (
                <option id={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
            <button type="submit" id="search">
              Search
            </button>
          </form>
        )}
      </div>
      {state.status === "success" && !state.data.length && <p>No Results</p>}
      {state.status === "success" && !!state.data.length && (
        <table id="results">
          <tr>
            <th>Continent</th>
            <th>Country</th>
            <th>Population</th>
            <th>New Cases</th>
            <th>Active Cases</th>
            <th>Critical Cases</th>
            <th>Recovered Cases</th>
            <th>1M Pop. Cases</th>
            <th>Total Cases</th>
            <th>New Deaths</th>
            <th>1M Pop. Deaths</th>
            <th>Total Deaths</th>
            <th>1M Pop. Tests</th>
            <th>Total Tests</th>
            <th>Day</th>
            <th>Time</th>
          </tr>
          {state.data?.map((data, key) => (
            <tr key={key}>
              <th>{data.continent}</th>
              <th>{data.country}</th>
              <th>{data.population}</th>
              <th>{data.cases.new}</th>
              <th>{data.cases.active}</th>
              <th>{data.cases.critical}</th>
              <th>{data.cases.recovered}</th>
              <th>{data.cases["1M_pop"]}</th>
              <th>{data.cases.total}</th>
              <th>{data.deaths.new}</th>
              <th>{data.deaths["1M_pop"]}</th>
              <th>{data.deaths.total}</th>
              <th>{data.tests["1M_pop"]}</th>
              <th>{data.tests.total}</th>
              <th>{data.day}</th>
              <th>{data.time}</th>
            </tr>
          ))}
        </table>
      )}
    </div>
  );
}

export default App;
