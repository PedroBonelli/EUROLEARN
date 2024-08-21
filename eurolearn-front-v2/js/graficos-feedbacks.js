async function getFeedbackData(url){
    const getFeedback = new Request(url);
    const response = await fetch(getFeedback);
    return await response.json();
}

async function filterFeedbacks(filter){
    if(filter.equalsIgnoreCase('Mensal')){
        
    }
}