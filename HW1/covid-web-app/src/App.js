import './App.css';
import SingleSelect from './SingleSelect';
import React, { useEffect, useState } from "react";

function App() {
  const [countries, setCountries] = useState([]);
  const [country, setCountry] = useState();
  const [tab, setTab] = useState("history");
  const [tableData, setTableData] = useState([]);

  useEffect(() => {
    const getCountries = async () => {
      const options = {
        method: 'GET',
        headers: {
          'X-RapidAPI-Host': 'covid-193.p.rapidapi.com',
          'X-RapidAPI-Key': 'e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22'
        }
      };

      try {
        const res = await fetch('http://localhost:8080/api/countries', options);
        const response = await res.json();
        const countries = response.response.map((country) => ({value: country.toLowerCase(), label: country}));
        setCountries(countries);
      } catch(e) {
        console.log(e)
      }
    }

    getCountries();
  },[])

  const handleSubmit = async (e) => {
    e.preventDefault();

    const options = {
      method: 'GET',
      headers: {
        'X-RapidAPI-Host': 'covid-193.p.rapidapi.com',
        'X-RapidAPI-Key': 'e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22'
      }
    };

    let url;
    
    if(tab === "history") {
      const date = e.currentTarget.date?.value

      if(date) {
        url = `http://localhost:8080/api/history?country=${country}&day=${date}`;
      } else {
        url = `http://localhost:8080/api/history?country=${country}`;
      }

    } else if(tab === "statistics") {

      if(country) {
        url = `http://localhost:8080/api/statistics?country=${country}`;
      } else {
        url = 'http://localhost:8080/api/statistics';        
      }
      
    }
    const resp = await fetch(url, options);
    const response = await resp.json();

    setTableData(response.response);
  }

  useEffect(() => {
    setCountry("")
  },[tab])

  return (
    <div className="App">
        <h1>
          Covid Incidence Statistics
        </h1>
      <div className='body'>
          <div className='tabs'>
            <button className={tab === "history" ? "active" : undefined} onClick={() => setTab("history")}>History</button>
            <button className={tab === "statistics" ? "active" : undefined} onClick={() => setTab("statistics")}>Statistics</button>
          </div>
          
          { tab === "history" &&<form onSubmit={handleSubmit}>
            <label>Country</label>
            <SingleSelect onChange={(option) => setCountry(option.value)} options={countries}/>        
            <label>Date (optional)</label>
            <input type="date" id="date" name="history-date"></input>
            <button type="submit">Search</button>
          </form>}
          { tab === "statistics" &&<form onSubmit={handleSubmit}>
            <label>Country (optional)</label>
            <SingleSelect onChange={(option) => setCountry(option?.value)} id="country" options={countries}/>        
            <button type="submit">Search</button>
          </form>}
      </div>
      <table>
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
        {tableData.map((data, key) => (
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
    </div>
  );
}

export default App;
