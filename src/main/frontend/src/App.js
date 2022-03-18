
import './App.css';
import axios from "axios";
import React, { useState } from "react";

function App() {
	const [state, setState] = useState({
		fizzNumber: "",
		buzzNumber: "",
		numbers: ""
	})

	const [fizzErrorState, setFizzErrorState] = useState()

	const [buzzErrorState, setBuzzErrorState] = useState()
	const [numErrorState, setNumErrorState] = useState()
	const [resultErrorSate, setResultErrorState] = useState()

	const [data, setData] = useState([])


	const handleChange = (e) => {
		const value = e.target.value;
		setState({
			...state,
			[e.target.name]: value,

		});
		if (e.target.name === "fizzNumber" && !isNumber(value)) {
			setFizzErrorState(`${e.target.name} :Please enter number only`);

		}
		else {
			setFizzErrorState("")
		}
		if (e.target.name === "buzzNumber" && !isNumber(value)) {

			setBuzzErrorState(`${e.target.name} :Please enter number only`);
		}
		else {
			setBuzzErrorState("")
		}
		if (e.target.name === "numbers" && !isNumberComma(e.target.value)) {
			setNumErrorState(`${e.target.name} :Please enter numbers seperated by comma`);
		}
		else {
			setNumErrorState("")
		}



	};


	const handleSubmit = (e) => {
		e.preventDefault();
		const userData = {
			fizzNumber: state.fizzNumber,
			buzzNumber: state.buzzNumber,
			input: state.numbers.split(/[ ,]+/)
		};

		axios.post("http://localhost:8101/v1/fizzBuzz", userData).then((response) => {
			console.log(response.status);
			console.log(response.data);
			setData(response.data)
		}).catch((exception) => {
			console.log(exception.response);
			console.log(exception.response.data);
			setResultErrorState(exception.response.data.errors);
		});
	};

	const isNumberComma = (inputNum) => {
		const numberRegex = /^[\w,]*$/;
		return numberRegex.test(inputNum);
	}

	const isNumber = (inputNum) => {
		const numberRegex = /^-?\d+$/
		return numberRegex.test(inputNum);
	};

	return (
		<div className="titleDiv">
		<label className="title">FIZZ BUZZ GAME</label>
		<div className="flex-container">
			
			<div>

				<form onSubmit={handleSubmit}>
					<label htmlFor="fizzNumber" className="textInput">
						enter Number between 1to 9 to be Fizz 
						<input
							type="text"
							name="fizzNumber"
							value={state.fizzNumber}
							onChange={handleChange}
						/>

					</label>
					<label className="error-message"> {fizzErrorState}</label>

					<label htmlFor="buzzNumber" className="textInput">
						Enter Number 1 to 9 to be Buzz
						<input
							type="text"
							name="buzzNumber"
							value={state.buzzNumber}
							onChange={handleChange}
						/>
					</label>
					<label className="error-message"> {buzzErrorState}</label>

					<label htmlFor="Numbers" className="textInput">
						Enter Numbers to Play with comma seperated(ex: 1,2,3)
						<input
							type="text" name="numbers"
							value={state.numbers}
							onChange={handleChange} />
					</label>
					<label className="error-message"> {numErrorState}</label>
					{
						fizzErrorState === "" && buzzErrorState === "" && numErrorState === "" &&
						<button type="submit" >Find FizzBuzz</button>
					}
				</form>


			</div>

			<div className="result">
				<div className="resultBox">
					{
						data.map((item) => {
							return <li className ={`${item}`}> {item}</li>
						})}
				</div>
				<label className="error-message"> {resultErrorSate}</label>
			</div>
		</div>
		</div>
	);




}


export default App;
