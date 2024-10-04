import Swal from 'sweetalert2';
import cash from "cash-dom";
import ky, { KyResponse, HTTPError as HttpError } from 'ky';



/** Muestra el spinner de carga */
function showLoadingSpinner() {
	cash("#pokemon-details").addClass('d-none'); // esconde los detalles del pokemon anterior
	cash("#pokemon-details-loading-spinner").removeClass('d-none'); // muestra el spinner de carga
}

/**
 * Se ejecuta cuando se encuentra un Pokemon
 * @param {KyResponse} r la respuesta del servidor
 */
async function onPokemonFound(r) {
	cash("#pokemon-details-loading-spinner").addClass('d-none'); // esconde el spinner de carga
	const { pokemonHtml } = await r.json()
	cash("#pokemon-details").replaceWith(pokemonHtml)
}

/**
 * Se ejecuta cuando no se encuentra un Pokemon
 * @param {HttpError} error el error devuelto por el servidor
 */
async function onSearchError(error) {
	cash("#pokemon-details-loading-spinner").addClass('d-none'); // esconde el spinner de carga
	const {message} = await error.response.json()
	Swal.fire({ icon: 'error', title: `Error ${error.response.status}`, text: message });
}

/** @returns {string} El input del Pokemón */
const getInput = () => cash('#pokemon-name-input').val().trim().toLowerCase()


/**
 * Se ejecuta cuando se envia el formulario
 * @param {Event} event El evento generado por la sumision del formulario
 */
function onPokemonFormSubmit(event) {
	event.preventDefault();
	const pokemonName = getInput();

	if (pokemonName !== "") {
		showLoadingSpinner()
		ky.get(`/home/pokemon/${pokemonName}`).then(onPokemonFound).catch(onSearchError)
	} else Swal.fire({ icon: 'warning', title: 'Campo Vacío', text: 'Por favor, ingresa el nombre o ID de un Pokémon' });
	
}


cash('#pokemon-form').on("submit", onPokemonFormSubmit);