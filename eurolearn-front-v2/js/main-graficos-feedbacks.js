import jsonToMap from './graficos-feedbacks.js'
import buildBarGraph from './graficos-feedbacks.js'

async function main(){
    // mapData = jsonToMap(await getFeedbackData('month'));

   let data =
       [
           [
               "2023-08-22",
               "2023-09-22",
               "2023-10-22",
               "2023-11-22",
               "2023-12-22",
               "2024-01-22",
               "2024-02-22",
               "2024-03-22",
               "2024-04-22",
               "2024-05-22",
               "2024-06-22",
               "2024-07-22"
           ],
           [0, 0, 2.555, 2.666, 0, 4.228, 3.5, 0, 0, 0, 0, 0]
       ];

    buildBarGraph(data, 'grafico1');
}

// inserir aqui a lógica async (pronto => exibir gráfico, esperando => loading, erro => erro na tela)
main();