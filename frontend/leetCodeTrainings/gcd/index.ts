function gcdOfStrings(str1: string, str2: string): string {
    if (str1 + str2 !== str2 + str1) {
        return ""
    }

    let s1L = str1.length;

    let s2L = str2.length;

    let gcds = function (x, y) {

        if (!y) {
            return x;
        }
        return gcds(y, x % y);
    }

    let div = gcds (s1L, s2L);

    return str1.slice(0, div);
};