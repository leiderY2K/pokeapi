import Swal from 'sweetalert2';
import cash from "cash-dom";


/**
 * @param {Event} event 
 */
function onPokemonFormSubmit(event) {
	event.preventDefault();
	let pokemonName = cash('#pokemon-name-input').val();
	if (typeof pokemonName === 'string') pokemonName = pokemonName.trim().toLowerCase()
	if (!pokemonName) Swal.fire({ icon: 'warning', title: 'Campo Vacío', text: 'Por favor, ingresa el nombre o ID de un Pokémon' });
	else {
		fetch(`/home/pokemon/${pokemonName}`)
			.then(r => r.text())
			.then(r => cash("#pokemon-details").replaceWith(r))
			.catch(err => console.log(err.text(), err.message()))
	}
}


cash('#pokemon-form').on("submit", onPokemonFormSubmit);