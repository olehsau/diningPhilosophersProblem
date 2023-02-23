dining kids problem

Program przyjmuje jeden parametr: nazwa pliku wejściowego do wczytania.

Plik w pierwszej linii zawiera ilość dzieci w danej grupie (jest to też ilość widelców). Następnie zawiera opisy dzieci; jeden przedszkolak na linię. Opis przedszkolaka zawiera jego imię oraz czas głodnienia. Imię oraz czas należy wykorzystać przy konstrukcji obiektów Child. Przedszkolaki siedzą w okręgu; ostatni sąsiaduje z przedostatnim oraz z pierwszym.

Każdy obiekt Child tworzy wątek, który sprawdza, czy dane dziecko nie jest zbyt głodne. Jeśli jest zbyt głodne - zapłacze na System.err. Zadanie jest poprawnie rozwiązane, jeśli żadne dziecko nie zapłacze w trakcie obiadu (wykonania programu). Obiad trwa 10 sekund.
