using namespace std;

#include <iostream>
#include <string>

int getMinutesLeft(string input);

int main()
{
    string input;
    while (true) {
        getline(cin, input);
        if (input == "00:00") {
            break;
        }
        try {
            cout << getMinutesLeft(input) << endl;
        } catch(exception e){
            // TODO: Not catched yet...
        }
    }
}

 int getMinutesLeft(string input)
 {
    string hoursString = input.substr(0,input.find_first_of(":"));
    string minutesString = input.substr(input.find_first_of(":") + 1);
    int hours = stoi(hoursString);
    int minutes = stoi(minutesString);
    if (hours >= 24 || hours < 0 || minutes >= 60 || minutes < 0){
        throw new exception();
    }
    return (23-hours)*60 + (60-minutes);
}
