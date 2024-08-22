import jsonToMap from './graficos-feedbacks.js'
import buildBarGraph from './graficos-feedbacks.js'

async function main(){
    // mapData = jsonToMap(await getFeedbackData('month'));

   let data =
        {
            "2023-11-21": [2.666],
            "2023-08-21": [],
            "2023-09-21": [],
            "2023-10-21": [2.555],
            "2023-12-21": [],
            "2024-01-21": [4.5, 4.1, 4.2],
            "2024-02-21": [3, 4],
            "2024-03-21": [],
            "2024-04-21": [],
            "2024-05-21": [],
            "2024-06-21": [],
            "2024-07-21": []
        }

    buildBarGraph(data, 'grafico1');
}

// inserir aqui a lógica async (pronto => exibir gráfico, esperando => loading, erro => erro na tela)
main();