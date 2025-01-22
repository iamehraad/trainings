function mergeAlternately(word1: string, word2: string): string {
    let mergedString = "";
    const bigString = word1.length > word2.length ? word1 : word2;
    for (let i = 0; i < bigString.length; i++) {
        if (word1[i]) {
            mergedString += word1[i];
        }
        if (word2[i]) {
            mergedString += word2[i]
        }
    }
    return mergedString;
};