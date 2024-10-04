const path = require('path');

module.exports = {
	entry: './src/main/resources/static/js/index.js', // Archivo de entrada
	output: {
		filename: 'bundle.js',
		path: path.resolve(__dirname, 'src/main/resources/static/dist'), // Archivo de salida
	},
	module: {
		rules: [
			{
				test: /\.css$/, // Manejo de archivos CSS
				use: ['style-loader', 'css-loader'],
			},
		],
	},	
	devServer: {
		static: './src/main/resources/static/dist', // Directorio para servir archivos
		port: 3000, // Cambia esto si necesitas otro puerto
	},
};
