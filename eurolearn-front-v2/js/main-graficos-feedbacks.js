import buildBarGraph from './graficos-feedbacks.js'

function generateGraph(data, htmlCanvasId, graphType){

    if (Chart.getChart(htmlCanvasId) != undefined){
        Chart.getChart(htmlCanvasId).destroy();
    }

    switch (graphType) {
        case 'bar':
           return buildBarGraph(data, htmlCanvasId);
        default:
            break;
    }
}

function generateMeanValue(data){
    return math.mean(data[1].filter((value) => value !== 0));
}

// inserir aqui a lógica async (pronto => exibir gráfico, esperando => loading, erro => erro na tela)
const buttonGenerateGraph = document.getElementById('generate-mean-graph');

buttonGenerateGraph.addEventListener('click', async () => {
    let filter = document.getElementById('monthsFilter').value;

    //apenas para teste, o certo aqui é chamar o método getFeedbacks do graficos-feedbacks.js
    let data = [];

    if (filter == 6) {
        data = [
            [
                "2024-02-22",
                "2024-03-22",
                "2024-04-22",
                "2024-05-22",
                "2024-06-22",
                "2024-07-22"
            ],
            [3.5, 0, 3.0, 4.23, 5.0, 0]
        ];
    } else if (filter == 12) {
        data = [
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
    }

    document.getElementById('meanValue').innerText = generateMeanValue(data);
    const graph = await generateGraph(data, 'barChartPerMonth', 'bar');

    const filterHeader = document.getElementById('filterNumber');
    filterHeader.innerText = filter;
})
