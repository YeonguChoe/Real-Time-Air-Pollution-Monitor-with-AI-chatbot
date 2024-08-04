import './App.css';
import React, {useEffect, useState} from "react";
import axios from "axios";
import cityList from './cities';
import {
    Autocomplete,
    TextField,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Box,
    Button
} from '@mui/material';

async function getPollutant(lat, long) {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/pollutant?latitude=${lat}&longitude=${long}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching pollutant data:', error);
        throw error;
    }
}

async function getAI(city, question, CO, NO2, O3, PM10, PM25, SO2) {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/ai`, {
            params: {
                city,
                question,
                CO,
                NO2,
                O3,
                PM10,
                PM25,
                SO2
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching AI data:', error);
        throw error;
    }
}

function App() {
    const [CO, setCO] = useState(0.0);
    const [NO2, setNO2] = useState(0.0);
    const [O3, setO3] = useState(0.0);
    const [PM10, setPM10] = useState(0.0);
    const [PM25, setPM25] = useState(0.0);
    const [SO2, setSO2] = useState(0.0);
    const [city, setCity] = useState("Toronto");
    const [pollutantData, setPollutantData] = useState(null);
    const [error, setError] = useState(null);
    const [question, setQuestion] = useState("");
    const [aiResponse, setAiResponse] = useState("");

    const cities = cityList;

    useEffect(() => {
        async function fetchPollutantData() {
            try {
                const selectedCity = cities.find(cityItem => cityItem.label === city);
                if (selectedCity) {
                    const {lat, long} = selectedCity;
                    const data = await getPollutant(lat, long);
                    setPollutantData(data);

                    setCO(data.carbonMonoxide || 0.0);
                    setNO2(data.nitrogenDioxide || 0.0);
                    setO3(data.ozone || 0.0);
                    setPM10(data.inhalableParticulateMatter || 0.0);
                    setPM25(data.fineParticulateMatter || 0.0);
                    setSO2(data.sulfurDioxide || 0.0);
                }
            } catch (error) {
                setError(error);
            }
        }

        fetchPollutantData();
    }, [city]);

    const handleCityChange = (event, newValue) => {
        if (newValue) {
            setCity(newValue.label);
        }
    };

    const handleAskClick = async () => {
        try {
            const response = await getAI(city, question, CO, NO2, O3, PM10, PM25, SO2);
            setAiResponse(response.reply);
        } catch (error) {
            setError(error);
        }
    };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();  // Prevents the default form submission behavior
            handleAskClick();
        }
    };

    if (error) return <div>Error: {error.message}</div>;

    return (
        <div className="App-container">
            <div className="App">
                <h1 className="title">Real-Time Air Pollution Monitor with AI Chatbot</h1>

                <div>
                    <h2 className="text-2xl mb-4">Select a City</h2>
                    <Autocomplete
                        options={cities}
                        getOptionLabel={(option) => option.label}
                        value={cities.find(cityItem => cityItem.label === city) || null}
                        onChange={handleCityChange}
                        renderInput={(params) => <TextField {...params} label="City" variant="outlined"/>}
                        className="w-full h-12"
                    />
                </div>

                {/* Pollutant Data Display */}
                {pollutantData && (
                    <TableContainer component={Paper} className="p-4">
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Pollutant Type</TableCell>
                                    <TableCell align="right">WHO Guideline</TableCell>
                                    <TableCell align="right">Real-time Amount</TableCell>
                                    <TableCell align="right">Unit</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <TableCell>Carbon Monoxide (CO)</TableCell>
                                    <TableCell align="right">9000</TableCell>
                                    <TableCell align="right">{CO}</TableCell>
                                    <TableCell align="right">ppb</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Nitrogen Dioxide (NO2)</TableCell>
                                    <TableCell align="right">180</TableCell>
                                    <TableCell align="right">{NO2}</TableCell>
                                    <TableCell align="right">ppb</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Ozone (O3)</TableCell>
                                    <TableCell align="right">180</TableCell>
                                    <TableCell align="right">{O3}</TableCell>
                                    <TableCell align="right">ppb</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Inhalable particulate matter (size below 10µm)</TableCell>
                                    <TableCell align="right">150</TableCell>
                                    <TableCell align="right">{PM10}</TableCell>
                                    <TableCell align="right">μg/m³</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Fine particulate matter (size below 2.5µm)</TableCell>
                                    <TableCell align="right">75</TableCell>
                                    <TableCell align="right">{PM25}</TableCell>
                                    <TableCell align="right">μg/m³</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Sulfur Dioxide (SO2)</TableCell>
                                    <TableCell align="right">500</TableCell>
                                    <TableCell align="right">{SO2}</TableCell>
                                    <TableCell align="right">ppb</TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                )}

                {/* AI Response Display */}
                <Box
                    sx={{
                        backgroundColor: 'black',
                        color: 'white',
                        padding: 2,
                        borderRadius: 1,
                        margin: '20px auto',
                        whiteSpace: 'pre-line',
                        overflowWrap: 'break-word',
                        wordBreak: 'break-word'
                    }}
                >
                    <h2>AI Response:</h2>
                    <div>{aiResponse || "I am a environment expert AI chatbot. Ask me anything about environment."}</div>
                </Box>

                {/* AI Interaction */}
                <div className="p-4">
                    <h2 className="text-2xl mb-4">Ask a Question</h2>
                    <Box
                        sx={{
                            display: 'flex',
                            gap: 2
                        }}
                    >
                        <TextField
                            value={question}
                            onChange={(e) => setQuestion(e.target.value)}
                            placeholder="Type your question here!"
                            variant="outlined"
                            fullWidth
                            size="small"
                            onKeyDown={handleKeyDown}
                        />
                        <Button
                            onClick={handleAskClick}
                            variant="contained"
                            color="primary"
                            sx={{height: '100%'}}
                        >
                            Ask
                        </Button>
                    </Box>
                </div>
                {/* Footer Section */}
                <footer>
                    <p>Made with ❤️ by Yeongu</p>
                </footer>
            </div>
        </div>
    );
}

export default App;
