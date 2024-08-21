class monthlyFilter {

    //ex data: "2024-01-20"

    doFilter(data, dateFieldName) {

        const monthsMap = new Map();

        monthsMap.set('01', null);
        monthsMap.set('02', null);
        monthsMap.set('03', null);
        monthsMap.set('04', null);
        monthsMap.set('05', null);
        monthsMap.set('06', null);
        monthsMap.set('07', null);
        monthsMap.set('08', null);
        monthsMap.set('09', null);
        monthsMap.set('10', null);
        monthsMap.set('11', null);
        monthsMap.set('12', null);

        for (key in monthsMap.keys()) {
            let monthFeedbackList = [];
            for (jsonItem in data) {
                let fullDate = jsonItem.dateFieldName;
                console.log(fullDate);
                const monthString = processDataString(fullDate);
                console.log(monthString);
                if (monthString.equalsIgnoreCase(key)) {
                    monthFeedbackList.add(jsonItem);
                }
                console.log(monthFeedbackList);
            }
            monthsMap.set(key, monthFeedbackList);
        }

        return monthsMap;
    }

    #processDataString(dataString) {
        return dataString.slice(5, 7);
    }

}