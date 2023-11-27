#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* concat(char* s1, char* s2) {
    //malloc
    char * newString = malloc( (strlen(s1) + strlen(s2) ) * sizeof(char) + 1 );
    //strcpy first string into maIIoc
    strcpy(newString, s1);
    //strcat the next string
    strcat(newString, s2);

    return newString;
}

int main (void) {
    char * s1 = "Hello";
    char * s2 = " World";
    char * newWord = concat(s1, s2);
    
    printf("%s", newWord);

    return 0;
}
