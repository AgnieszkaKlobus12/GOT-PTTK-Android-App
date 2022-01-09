package com.example.poapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.poapp.model.dao.*
import com.example.poapp.model.entity.*
import kotlinx.coroutines.*

@Database(
    entities = [User::class, Proof::class, MountainPassProof::class, MountainGroup::class, MountainPassOfficial::class, RouteSection::class, MountainPassUser::class, Badge::class, MountainRange::class, Employee::class, Leader::class, OfficialPoint::class, UserPoint::class, Route::class, Tourist::class, LeaderPermissions::class],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun touristDAO(): TouristDAO
    abstract fun routeDAO(): RouteDAO
    abstract fun mountainPassDAO(): MountainPassOfficialDAO
    abstract fun officialPointDAO(): OfficialPointDAO
    abstract fun routeSectionDAO(): RouteSectionDAO
    abstract fun mountainGroupDAO(): MountainGroupDAO
    abstract fun mountainRangeDAO(): MountainRangeDAO

    @DelicateCoroutinesApi
    companion object {
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build()
            return INSTANCE!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch(Dispatchers.IO) {
                    populateDatabase(INSTANCE!!)
                }
            }

            private suspend fun populateDatabase(db: AppDatabase) {
                db.let { db ->
                    withContext(Dispatchers.IO) {
                        val mountainGroupDAO = db.mountainGroupDAO()
                        val mountainRangeDAO = db.mountainRangeDAO()
                        val userDAO = db.userDAO()
                        val touristDAO = db.touristDAO()
                        val officialPointDAO = db.officialPointDAO()
                        val mountainPassDAO = db.mountainPassDAO()

                        //every table needs to be cleared to avoid redundant data
                        mountainGroupDAO.deleteAll()
                        mountainRangeDAO.deleteAll()
                        userDAO.deleteAll()
                        touristDAO.deleteAll()
                        officialPointDAO.deleteAll()
                        mountainPassDAO.deleteAll()

                        //id is autogenerated so when creating object it can be always 0
                        val tatry_podtatrze = mountainGroupDAO.insert(MountainGroup(0, "Tatry i Podtatrze", "Polska", byteArrayOf()))
                        val tatry_wysokie = mountainRangeDAO.insert(MountainRange(0, "Tatry Wysokie", byteArrayOf(), tatry_podtatrze))
                        mountainRangeDAO.insert(MountainRange(0, "Tatry Zachodnie", byteArrayOf(), tatry_podtatrze))
                        mountainRangeDAO.insert(MountainRange(0, "Podtatrze", byteArrayOf(), tatry_podtatrze))

                        val tatry_slowackie = mountainGroupDAO.insert(MountainGroup(0, "Tatry Słowackie", "Słowacja", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Tatry Zachodnie - Słowacja", byteArrayOf(), tatry_slowackie))
                        mountainRangeDAO.insert(MountainRange(0, "Tatry Wysokie - Słowacja", byteArrayOf(), tatry_slowackie))
                        mountainRangeDAO.insert(MountainRange(0, "Tatry Bielskie - Słowacja", byteArrayOf(), tatry_slowackie))
                        mountainRangeDAO.insert(MountainRange(0, "Niskie Zachodnie - Słowacja", byteArrayOf(), tatry_slowackie))
                        mountainRangeDAO.insert(MountainRange(0, "Tatry Słowackie - szlaki z przewodnikiem", byteArrayOf(), tatry_slowackie))

                        val beskidy_zachodnie = mountainGroupDAO.insert(MountainGroup(0, "Beskidy Zachodnie", "Polska", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Śląski", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Żywiecki", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Mały", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Średni", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Gorce", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Wyspowy", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Orawa", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Spisz i Pieniny", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Sądecki", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Podgórze Wielickie", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Podgórze Wiśnickie", byteArrayOf(), beskidy_zachodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Podgórze Rożnowskie", byteArrayOf(), beskidy_zachodnie))

                        val beskidy_wschodnie = mountainGroupDAO.insert(MountainGroup(0, "Beskidy Wschodnie", "Polska", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Podgórze Ciężkowickie", byteArrayOf(), beskidy_wschodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Niski część zachodnia", byteArrayOf(), beskidy_wschodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Beskid Niski część wschodnia", byteArrayOf(), beskidy_wschodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Bieszczady", byteArrayOf(), beskidy_wschodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Pogórze Strzyżowsko-Dynowskie", byteArrayOf(), beskidy_wschodnie))
                        mountainRangeDAO.insert(MountainRange(0, "Pogórze Przemyskie", byteArrayOf(), beskidy_wschodnie))

                        val gory_swietokrzyskie = mountainGroupDAO.insert(MountainGroup(0, "Góry Świętokrzyskie", "Polska", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Świętokrzyskie Ł01", byteArrayOf(), gory_swietokrzyskie))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Świętokrzyskie Ł02", byteArrayOf(), gory_swietokrzyskie))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Świętokrzyskie Ł03", byteArrayOf(), gory_swietokrzyskie))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Świętokrzyskie Ł04", byteArrayOf(), gory_swietokrzyskie))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Świętokrzyskie Ł05", byteArrayOf(), gory_swietokrzyskie))

                        val sudety = mountainGroupDAO.insert(MountainGroup(0, "Sudety", "Polska", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Izerskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Pogórze Izerskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Karkonosze", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Kotlina Jeleniogórska", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Rudawy Janowickie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Kaczawskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Pogórze Kaczawskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Kotlina Kamiennogórska", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Kamienne", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Wałbrzyskie, Pogórze Bolkowsko-Wałbrzyskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Sowie, Wzgórza Włodzickie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Bardzkie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Stołowe, Wzgórza Lewińskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Orlickie, Góry Bystrzyckie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Kotlina Kłodzka, Góry Bialskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Masyw Śnieżnika, Góry Bialskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Złote", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Góry Opawskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Wzgórza Strzegomskie", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Masyw Ślęży, Równina Świdnicka, Kotlina Dzierżoniowska", byteArrayOf(), sudety))
                        mountainRangeDAO.insert(MountainRange(0, "Wzgórza Niemczańsko-Strzelińskie, Przedgórze Paczkowskie", byteArrayOf(), sudety))

                        val slowacja = mountainGroupDAO.insert(MountainGroup(0, "Słowacja", "Słowacja", byteArrayOf()))
                        mountainRangeDAO.insert(MountainRange(0, "Słowacki Raj", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Mała Fatra - Vrátna", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Mała Fatra - Martinské hole", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Veľká Fatra i Choć", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Kysucke Beskydy - Grzbiet Graniczny", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Oravské Beskydy - Słowacja", byteArrayOf(), slowacja))
                        mountainRangeDAO.insert(MountainRange(0, "Pieniny - Słowacja", byteArrayOf(), slowacja))

                        val user = userDAO.insert(User(0, "jkowalski", "p@ssw0rd", "jkowalski@gmail.com", "Jan", "Kowalski", "27.02.1995", 0))
                        touristDAO.insert(Tourist(0, user.toInt(), 0, false))

                        val rusinowa_polana = officialPointDAO.insert(OfficialPoint(0, "Rusinowa Polana", 49.261037737025546, 20.08954157827796, tatry_wysokie.toInt()))
                        val dolina_filipka = officialPointDAO.insert(OfficialPoint(0, "Dolina Filipka", 49.28241531042575, 20.088050961369692, tatry_wysokie.toInt()))
                        val wierch_poroniec = officialPointDAO.insert(OfficialPoint(0, "Wierch Poroniec", 49.283190141722066, 20.112731671357615, tatry_wysokie.toInt()))
                        val palenica_bialczanska = officialPointDAO.insert(OfficialPoint(0, "Palenica Białczańska", 49.25518007371257, 20.102995078994887, tatry_wysokie.toInt()))
                        val polana_pod_woloszynem = officialPointDAO.insert(OfficialPoint(0, "Polana pod Wołoszynem", 49.248349244046196, 20.085922284849925, tatry_wysokie.toInt()))
                        val lysa_polana = officialPointDAO.insert(OfficialPoint(0, "Łysa Polana", 49.271083344452315, 20.117899259285892, tatry_wysokie.toInt()))
                        val gesia_szyja = officialPointDAO.insert(OfficialPoint(0, "Gęsia Szyja", 49.25949725565063, 20.07644739900862, tatry_wysokie.toInt()))
                        val rowien_waksmundzka = officialPointDAO.insert(OfficialPoint(0, "Rówień Waksmundzka", 49.25549193877567, 20.06545949649405, tatry_wysokie.toInt()))
                        val psia_trawka = officialPointDAO.insert(OfficialPoint(0, "Psia Trawka", 49.2697816362452, 20.03655201852704, tatry_wysokie.toInt()))
                        val czerwony_staw_dolina_panszczycy = officialPointDAO.insert(OfficialPoint(0, "Czerwony Staw w Dolinie Pańszczyzny", 49.240546040702334, 20.03555023598516, tatry_wysokie.toInt()))
                        val schronisko_PTTK_hala_gasienicowa = officialPointDAO.insert(OfficialPoint(0, "Schronisko PTTK na Hali Gąsienicowej", 49.243516452095825, 20.007120225329288, tatry_wysokie.toInt()))
                        val wodogrzmoty_mickiewicza = officialPointDAO.insert(OfficialPoint(0, "Wodogrzmoty Mickiewicza", 49.234347696567085, 20.087030069507108, tatry_wysokie.toInt()))
                        val schronisko_PTTK_roztoka = officialPointDAO.insert(OfficialPoint(0, "Schronisko PTTK w Roztoce", 49.233869297991966, 20.095642811835877, tatry_wysokie.toInt()))
                        val schronisko_PTTK_morskie_oko = officialPointDAO.insert(OfficialPoint(0, "Schronisko PTTK nad Morskim Okiem", 49.20150152512064, 20.071315113684303, tatry_wysokie.toInt()))
                        val czarny_staw_morskie_oko = officialPointDAO.insert(OfficialPoint(0, "Czarny Staw nad Morskim Okiem", 49.18844477236488, 20.075447377999925, tatry_wysokie.toInt()))
                        val rysy = officialPointDAO.insert(OfficialPoint(0, "Rysy", 49.17996791784133, 20.08819272061726, tatry_wysokie.toInt()))
                        val mieguszowiecka_przelecz_pod_chlopkiem = officialPointDAO.insert(OfficialPoint(0, "Mięguszowiecka Przełęcz pod Chłopkiem", 49.183797798103306, 20.065419542519432, tatry_wysokie.toInt()))
                        val dolina_za_mnichem = officialPointDAO.insert(OfficialPoint(0, "Dolina za Mnichem", 49.19503448063504, 20.04958089649246, tatry_wysokie.toInt()))
                        val wrota_chalubinskiego = officialPointDAO.insert(OfficialPoint(0, "Wrota Chałubińskiego", 49.191916446114966, 20.045126457861983, tatry_wysokie.toInt()))
                        val szpiglasowa_przelecz = officialPointDAO.insert(OfficialPoint(0, "Szpiglasowa Przełęcz", 49.198046483170835, 20.042353525328142, tatry_wysokie.toInt()))
                        val szpiglasowy_wierch = officialPointDAO.insert(OfficialPoint(0, "Szpiglasowy Wierch", 49.197460428042206, 20.040018367656977, tatry_wysokie.toInt()))
                        val tablica_s_bronikowskiego = officialPointDAO.insert(OfficialPoint(0, "Tablica S. Bronikowskiego", 49.209922, 20.026695, tatry_wysokie.toInt()))
                        val schronisko_PTTK_dolina_pieciu_stawow_polskich = officialPointDAO.insert(OfficialPoint(0, "Schronisko PTTK w Dolinie Pięciu Stawów Polskich", 49.21380061732274, 20.049488303341363, tatry_wysokie.toInt()))
                        val siklawa = officialPointDAO.insert(OfficialPoint(0, "Siklawa", 49.21466416356454, 20.04418787320509, tatry_wysokie.toInt()))
                        val dolina_roztoki = officialPointDAO.insert(OfficialPoint(0, "Dolina Roztoki", 49.22566821557562, 20.073154885397123, tatry_wysokie.toInt()))
                        val kozi_wierch = officialPointDAO.insert(OfficialPoint(0, "Kozi Wierch", 49.21908356655015, 20.02868629789423, tatry_wysokie.toInt()))
                        val kozia_przelecz = officialPointDAO.insert(OfficialPoint(0, "Kozia Przełęcz", 49.21995389579278, 20.02521817784278, tatry_wysokie.toInt()))
                        val kozia_dolinka = officialPointDAO.insert(OfficialPoint(0, "Kozia Dolinka", 49.22278720535416, 20.02700441058001, tatry_wysokie.toInt()))
                        val przelecz_zawrat = officialPointDAO.insert(OfficialPoint(0, "Przełęcz Zawrat", 49.21953480685963, 20.01662349930768, tatry_wysokie.toInt()))
                        val czarny_staw_gasienicowy = officialPointDAO.insert(OfficialPoint(0, "Czarny Staw Gąsienicowy", 49.23124496962578, 20.01769098484947, tatry_wysokie.toInt()))
                        val swinica = officialPointDAO.insert(OfficialPoint(0, "Swinica", 49.21988328992599, 20.00945333597645, tatry_wysokie.toInt()))
                        val swinicka_przelecz = officialPointDAO.insert(OfficialPoint(0, "Świnicka Przełęcz", 49.22163260198108, 20.003893305292273, tatry_wysokie.toInt()))
                        val przelecz_liliowe = officialPointDAO.insert(OfficialPoint(0, "Przełęcz Liliowe", 49.22538825757954, 19.992401109986353, tatry_wysokie.toInt()))
                        val zielony_staw_gasienicowy = officialPointDAO.insert(OfficialPoint(0, "Zielony Staw Gąsienicowy", 49.228793125422655, 19.99904052932266, tatry_wysokie.toInt()))
                        val zleb_kulczynskiego = officialPointDAO.insert(OfficialPoint(0, "Żleb Kulczyńskiego", 49.221075928614, 20.03180096950671, tatry_wysokie.toInt()))
                        val zadni_granat = officialPointDAO.insert(OfficialPoint(0, "Zadni Granat", 49.2249540468493, 20.03208873652706, tatry_wysokie.toInt()))
                        val skrajny_granat = officialPointDAO.insert(OfficialPoint(0, "Skrajny Granat", 49.22781275337523, 20.033719512691768, tatry_wysokie.toInt()))
                        val przelecz_krzyzne = officialPointDAO.insert(OfficialPoint(0, "Przełęcz Krzyżne", 49.22947322700498, 20.046840921186423, tatry_wysokie.toInt()))
                        val przelecz_karb = officialPointDAO.insert(OfficialPoint(0, "Przełęcz Karb", 49.22955907396493, 20.01126453652894, tatry_wysokie.toInt()))
                        val koscielec = officialPointDAO.insert(OfficialPoint(0, "Kościelec", 49.28868351042768, 19.96667989554807, tatry_wysokie.toInt()))
                        val dwoisniak = officialPointDAO.insert(OfficialPoint(0, "Dwoiśniak", 49.237799535299736, 19.99735660601397, tatry_wysokie.toInt()))
                        val brzeziny = officialPointDAO.insert(OfficialPoint(0, "Brzeziny", 49.28819263388352, 20.035223055457887, tatry_wysokie.toInt()))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, dolina_filipka.toInt(), rusinowa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, rusinowa_polana.toInt(), dolina_filipka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, wierch_poroniec.toInt(), rusinowa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, rusinowa_polana.toInt(), wierch_poroniec.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, palenica_bialczanska.toInt(), rusinowa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, rusinowa_polana.toInt(), palenica_bialczanska.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, polana_pod_woloszynem.toInt(), rusinowa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, rusinowa_polana.toInt(), polana_pod_woloszynem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, wierch_poroniec.toInt(), lysa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, lysa_polana.toInt(), wierch_poroniec.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, palenica_bialczanska.toInt(), lysa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, lysa_polana.toInt(), palenica_bialczanska.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, rusinowa_polana.toInt(), gesia_szyja.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, gesia_szyja.toInt(), rusinowa_polana.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, rowien_waksmundzka.toInt(), gesia_szyja.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, gesia_szyja.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, psia_trawka.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, rowien_waksmundzka.toInt(), psia_trawka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, polana_pod_woloszynem.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, rowien_waksmundzka.toInt(), polana_pod_woloszynem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, czerwony_staw_dolina_panszczycy.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, rowien_waksmundzka.toInt(), czerwony_staw_dolina_panszczycy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, czerwony_staw_dolina_panszczycy.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, rowien_waksmundzka.toInt(), czerwony_staw_dolina_panszczycy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 7, schronisko_PTTK_hala_gasienicowa.toInt(), rowien_waksmundzka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, rowien_waksmundzka.toInt(), schronisko_PTTK_hala_gasienicowa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, schronisko_PTTK_hala_gasienicowa.toInt(), psia_trawka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 7, psia_trawka.toInt(), schronisko_PTTK_hala_gasienicowa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, brzeziny.toInt(), psia_trawka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, psia_trawka.toInt(), brzeziny.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, palenica_bialczanska.toInt(), wodogrzmoty_mickiewicza.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, wodogrzmoty_mickiewicza.toInt(), palenica_bialczanska.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, polana_pod_woloszynem.toInt(), wodogrzmoty_mickiewicza.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, wodogrzmoty_mickiewicza.toInt(), polana_pod_woloszynem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, schronisko_PTTK_roztoka.toInt(), wodogrzmoty_mickiewicza.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, wodogrzmoty_mickiewicza.toInt(), schronisko_PTTK_roztoka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, wodogrzmoty_mickiewicza.toInt(), schronisko_PTTK_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, schronisko_PTTK_morskie_oko.toInt(), wodogrzmoty_mickiewicza.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "Wokół Morskiego Oka", 2, schronisko_PTTK_morskie_oko.toInt(), schronisko_PTTK_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, schronisko_PTTK_morskie_oko.toInt(), czarny_staw_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, czarny_staw_morskie_oko.toInt(), schronisko_PTTK_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, rysy.toInt(), czarny_staw_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 13, czarny_staw_morskie_oko.toInt(), rysy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, mieguszowiecka_przelecz_pod_chlopkiem.toInt(), czarny_staw_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 10, czarny_staw_morskie_oko.toInt(), mieguszowiecka_przelecz_pod_chlopkiem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, schronisko_PTTK_morskie_oko.toInt(), dolina_za_mnichem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, dolina_za_mnichem.toInt(), schronisko_PTTK_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, wrota_chalubinskiego.toInt(), dolina_za_mnichem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, dolina_za_mnichem.toInt(), wrota_chalubinskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, szpiglasowy_wierch.toInt(), szpiglasowa_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, szpiglasowa_przelecz.toInt(), szpiglasowy_wierch.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, tablica_s_bronikowskiego.toInt(), szpiglasowa_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, szpiglasowa_przelecz.toInt(), tablica_s_bronikowskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, dolina_za_mnichem.toInt(), szpiglasowa_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, szpiglasowa_przelecz.toInt(), dolina_za_mnichem.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, siklawa.toInt(), schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), siklawa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 9, schronisko_PTTK_morskie_oko.toInt(), schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), schronisko_PTTK_morskie_oko.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "Od szlaku zielonego", 3, dolina_roztoki.toInt(), schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "Od szlaku zielonego", 1, schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), dolina_roztoki.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, tablica_s_bronikowskiego.toInt(), siklawa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, siklawa.toInt(), tablica_s_bronikowskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 10, wodogrzmoty_mickiewicza.toInt(), siklawa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 14, siklawa.toInt(), wodogrzmoty_mickiewicza.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, siklawa.toInt(), kozi_wierch.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, kozi_wierch.toInt(), siklawa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, kozia_przelecz.toInt(), kozi_wierch.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, tablica_s_bronikowskiego.toInt(), kozia_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, kozia_przelecz.toInt(), tablica_s_bronikowskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, kozia_dolinka.toInt(), kozia_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, kozia_przelecz.toInt(), kozia_dolinka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, przelecz_zawrat.toInt(), kozia_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, czarny_staw_gasienicowy.toInt(), przelecz_zawrat.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, przelecz_zawrat.toInt(), czarny_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 7, tablica_s_bronikowskiego.toInt(), przelecz_zawrat.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, przelecz_zawrat.toInt(), tablica_s_bronikowskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, przelecz_zawrat.toInt(), swinica.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, swinica.toInt(), przelecz_zawrat.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, swinicka_przelecz.toInt(), swinica.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, swinica.toInt(), swinicka_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, przelecz_liliowe.toInt(), swinicka_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, swinicka_przelecz.toInt(), przelecz_liliowe.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, zielony_staw_gasienicowy.toInt(), swinicka_przelecz.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, swinicka_przelecz.toInt(), zielony_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, czarny_staw_gasienicowy.toInt(), kozia_dolinka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, zleb_kulczynskiego.toInt(), kozia_dolinka.toInt(), zadni_granat.toInt(), tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, kozia_dolinka.toInt(), zleb_kulczynskiego.toInt(), zadni_granat.toInt(), tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, kozi_wierch.toInt(), zleb_kulczynskiego.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, przelecz_liliowe.toInt(), kozia_dolinka.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, kozia_dolinka.toInt(), przelecz_liliowe.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, kozia_dolinka.toInt(), skrajny_granat.toInt(), zadni_granat.toInt(), tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, zleb_kulczynskiego.toInt(), skrajny_granat.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, czarny_staw_gasienicowy.toInt(), skrajny_granat.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, skrajny_granat.toInt(), czarny_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 9, schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), przelecz_krzyzne.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, przelecz_krzyzne.toInt(), schronisko_PTTK_dolina_pieciu_stawow_polskich.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 8, czerwony_staw_dolina_panszczycy.toInt(), przelecz_krzyzne.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, przelecz_krzyzne.toInt(), czerwony_staw_dolina_panszczycy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, skrajny_granat.toInt(), przelecz_krzyzne.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, koscielec.toInt(), przelecz_karb.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, przelecz_karb.toInt(), koscielec.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, zielony_staw_gasienicowy.toInt(), przelecz_karb.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, przelecz_karb.toInt(), zielony_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, czarny_staw_gasienicowy.toInt(), przelecz_karb.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, przelecz_karb.toInt(), czarny_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, przelecz_liliowe.toInt(), dwoisniak.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 5, dwoisniak.toInt(), przelecz_liliowe.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, schronisko_PTTK_hala_gasienicowa.toInt(), dwoisniak.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, dwoisniak.toInt(), schronisko_PTTK_hala_gasienicowa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 1, zielony_staw_gasienicowy.toInt(), dwoisniak.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, dwoisniak.toInt(), zielony_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))

                        mountainPassDAO.insert(MountainPassOfficial(0, "", 2, czarny_staw_gasienicowy.toInt(), schronisko_PTTK_hala_gasienicowa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 3, schronisko_PTTK_hala_gasienicowa.toInt(), czarny_staw_gasienicowy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 4, czerwony_staw_dolina_panszczycy.toInt(), schronisko_PTTK_hala_gasienicowa.toInt(), null, tatry_wysokie.toInt(), "aktywny"))
                        mountainPassDAO.insert(MountainPassOfficial(0, "", 6, schronisko_PTTK_hala_gasienicowa.toInt(), czerwony_staw_dolina_panszczycy.toInt(), null, tatry_wysokie.toInt(), "aktywny"))


                    }
                }
            }
        }
    }
}