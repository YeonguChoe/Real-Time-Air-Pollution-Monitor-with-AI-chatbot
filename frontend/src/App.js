import './App.css';

function App() {
    return (
        <div className="App">
            <h1>Real-Time Air Pollution Monitor with an AI Chatbot</h1>

            <div className="App p-4">
                <h1 className="text-2xl mb-4">Select an Option</h1>
                <select
                    value={"option1"}
                    onChange={()=>{}}
                    className="w-full h-12 bg-gray-200 border border-gray-300 rounded px-4"
                >
                    <option value="option1">Option 1</option>
                    <option value="option2">Option 2</option>
                    <option value="option3">Option 3</option>
                </select>
                <div className="mt-4">
                    {/*<p className="text-lg">Selected: {selectedOption}</p>*/}
                </div>
            </div>


            {/*Text Box*/}
            <input type={"text"}/>
            {/* Question Field */}
            <input type={"text"}/>
            <button onClick={console.log(3)}>Ask</button>
        </div>
    );
}

export default App;
