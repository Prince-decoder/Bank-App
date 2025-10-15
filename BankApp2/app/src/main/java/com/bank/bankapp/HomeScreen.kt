package com.bank.bankapp


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CreditCardOff
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Rotate90DegreesCw
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import kotlinx.coroutines.launch
import com.example.bank_app.R

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val userName = "UserName"
    val password = "12345678"

    HomeScreen(navController)
}
@Composable
fun HomeScreen(navController: NavHostController) {
    val hazeStateBackground = remember { HazeState() }
    val hazeStateForeground = remember { HazeState() }

    val navigateToPay = "payment_screen"
    val navigateToUPI = "pin_screen"
    val navigateToContacts = "phonebook_screen"

    val items = listOf(
        NavigationItems(
            title = "Data Logging",
            selectedIcon = painterResource(R.drawable.finance_mode_100dp),
            unselectedIcon = painterResource(R.drawable.finance_mode_100dp),
        ),
        NavigationItems(
            title = "Dashboard",
            selectedIcon = painterResource(R.drawable.dashboard_100dp_filled),
            unselectedIcon = painterResource(R.drawable.dashboard_100dp),
        ),
        NavigationItems(
            title = "Analytics",
            selectedIcon = painterResource(R.drawable.analytics_100d_filled),
            unselectedIcon = painterResource(R.drawable.analytics_100dp),
        ),
        NavigationItems(
            title = "Settings",
            selectedIcon = painterResource(R.drawable.settings_100dp_filled),
            unselectedIcon = painterResource(R.drawable.settings_100dp),
        )
    )

    //Remember Clicked index state
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = Shapes().extraLarge,
                drawerContainerColor = Color.Transparent,
                drawerContentColor = Color.Red,
                modifier = Modifier
                    .hazeEffect(
                        state = hazeStateBackground,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 0),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 30.dp,
                            noiseFactor = 0f
                        )
                    ),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    painterResource(R.drawable.symbol),
                    contentDescription = "App Logo",
                    tint = Color(42, 40, 152, 255),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(130.dp)
                        .align(Alignment.CenterHorizontally)
                )

                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight.Normal) },
                        selected = index == selectedItemIndex,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Transparent,
                            unselectedContainerColor = Color.Transparent,
                            selectedTextColor = Color(49, 28, 102, 255),
                            unselectedTextColor = Color(35, 0, 122, 255),
                            selectedIconColor = Color(20, 30, 122, 255),
                            unselectedIconColor = Color(20, 30, 122, 255)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            selectedItemIndex = index
                            if (item.title == "Dashboard") {
                                navController.navigate("dashboard")
                            }
                        },
                        icon = {
                            Icon(
                                painter = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .size(300.dp, 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        //.padding(vertical = 24.dp, horizontal = 64.dp)
                        .fillMaxWidth()
                        .size(380.dp, 65.dp)
                        .height(64.dp)
                        .hazeEffect(
                            state = hazeStateForeground,
                            style = HazeStyle(
                                White.copy(alpha = 0.1f),
                                tint = HazeTint(
                                    Color(128, 128, 128, 0),
                                    BlendMode.Luminosity
                                ),
                                blurRadius = 30.dp,
                                noiseFactor = 0f
                            )
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            } },
                        modifier = Modifier
                            .size(60.dp)
                            .clickable(true, onClick = {})
                            .padding(4.dp, 30.dp, 4.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,       //edited
                            contentDescription = "Menu Button",
                            tint = Color.White,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(60.dp)
                            .clickable(true, onClick = {})
                            .offset(130.dp, 0.dp)
                            .padding(4.dp, 30.dp, 4.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Menu Button",
                            tint = Color.White,
                            modifier = Modifier
                                .size(60.dp)
                        )
                    }
                    BadgedBox(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp, 30.dp, 4.dp, 0.dp),
                        badge = {
                            Badge(
                                modifier = Modifier
                                    .size(10.dp)
                                    .offset((25).dp, 0.dp),
                                containerColor = Color.Red,
                                contentColor = White,
                                content = { Text("1") }
                            )
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable(true, onClick = {}),
                                tint = White
                            )
                        }
                    )
                }
            },
            bottomBar = {
                var selectedTabIndex by remember { mutableIntStateOf(1) }
                Box(
                    modifier = Modifier
                        .padding(vertical = 44.dp, horizontal = 64.dp)
                        .requiredSize(380.dp, 65.dp)
                        .height(64.dp)
                        .clip(shape = BottomNavBarShape(
                            cornerRadius = 33.dp,
                            dipHeight = 50.dp,
                            dipWidth = 60.dp,
                            dipControlOffset = 13.dp // Sets the beginning of the dip curve
                        ))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.7f),
                                    Color.White.copy(alpha = 0.3f),
                                ),
                            ),
                            shape = RoundedCornerShape(66.dp)
                        )
                        .hazeEffect(
                            state = hazeStateBackground,
                            style = HazeStyle(
                                White.copy(alpha = 0.1f),
                                tint = HazeTint(
                                    Color(128, 128, 128, 0),
                                    BlendMode.Luminosity
                                ),
                                blurRadius = 30.dp,
                                noiseFactor = 100f
                            )
                        ),
                ) {
                    BottomBarTabs(
                        tabs,
                        selectedTab = selectedTabIndex,
                        onTabSelected = {
                            selectedTabIndex = tabs.indexOf(it)
                        }
                    )
                    val animatedSelectedTabIndex by animateFloatAsState(
                        targetValue = selectedTabIndex.toFloat(),
                        label = "animatedSelectedTabIndex",
                        animationSpec = spring(
                            stiffness = Spring.StiffnessLow,
                            dampingRatio = Spring.DampingRatioLowBouncy,
                        )
                    )

                    val animatedColor by animateColorAsState(
                        targetValue = tabs[selectedTabIndex].color,
                        label = "animatedColor",
                        animationSpec = spring(
                            stiffness = Spring.StiffnessLow,
                        )
                    )

                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .blur(60.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    ) {
                        val tabWidth = size.width / tabs.size
                        drawCircle(
                            color = animatedColor.copy(alpha = .8f),
                            radius = size.height / 2,
                            center = Offset(
                                (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                                size.height / 2
                            )
                        )
                    }

                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    ) {
                        val path = Path().apply {
                            addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
                        }
                        val length = PathMeasure().apply { setPath(path, false) }.length

                        val tabWidth = size.width / tabs.size
                        drawPath(
                            path,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    animatedColor.copy(alpha = 0f),
                                    animatedColor.copy(alpha = 1f),
                                    animatedColor.copy(alpha = 1f),
                                    animatedColor.copy(alpha = 0f),
                                ),
                                startX = tabWidth * animatedSelectedTabIndex,
                                endX = tabWidth * (animatedSelectedTabIndex + 1),
                            ),
                            style = Stroke(
                                width = 6f,
                                pathEffect = PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(length / 2, length)
                                )
                            )
                        )
                    }

                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*navController.navigate("payment_screen") */},
                    modifier = Modifier
                        .size(60.dp)
                        .offset((-138).dp, 85.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.7f),
                                    Color.White.copy(alpha = 0.1f),
                                ),
                            ),
                            shape = RoundedCornerShape(66.dp)
                        )
                        .hazeEffect(
                            state = hazeStateForeground,
                            style = HazeStyle(
                                White.copy(alpha = 0.1f),
                                tint = HazeTint(
                                    Color(128, 128, 128, 0),
                                    BlendMode.Luminosity
                                ),
                                blurRadius = 30.dp,
                                noiseFactor = 100f
                            )
                        ),
                    containerColor = Color.Transparent,
                    contentColor = White,
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "QR Scanner",
                        modifier = Modifier
                            .size(45.dp)
                    )
                }
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),

                ) {
                Image(
                    painterResource(R.drawable.background1),
                    contentDescription = "Background",
                    modifier = Modifier
                        //.rotate(180f)
                        .requiredSize(900.dp)
                        .hazeSource(state = hazeStateBackground)
                )

                Column(
                    Modifier
                        .hazeSource(hazeStateForeground)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Spacer(
                        modifier = Modifier
                            .size(0.dp, 70.dp)
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 150.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 120),
                                        BlendMode.Luminosity),
                                    blurRadius = 20.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        val facultyGlyphic = androidx.compose.ui.text.font.FontFamily.Monospace
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text("UserName",
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 10.dp, 2.dp)
                                    .size(150.dp, 25.dp),
                                color = White,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Start,
                                    fontFamily =facultyGlyphic,
                                    fontWeight = FontWeight.Bold
                                ))

                            Text("Balance: ",
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 10.dp, 2.dp)
                                    .size(150.dp, 25.dp),
                                color = White,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Start,
                                    fontFamily = facultyGlyphic,
                                    fontWeight = FontWeight.Bold
                                ))

                            Text("Savings A/C",
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 10.dp, 2.dp)
                                    .size(150.dp, 25.dp),
                                color = White,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Start,
                                    fontFamily = facultyGlyphic,
                                    fontWeight = FontWeight.Bold
                                ))

                            Text("A/C No: XXXXXX7890",
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 10.dp, 2.dp)
                                    .size(150.dp, 35.dp),
                                color = White,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Start,
                                    fontFamily = facultyGlyphic,
                                    fontWeight = FontWeight.Bold
                                ))
                        }

                        IconButton(
                            onClick = { /* Prompt for image from gallery */ },
                            modifier = Modifier
                                .padding(1.dp, 5.dp, 10.dp, 2.dp)
                                .size(100.dp, 200.dp),
                            content = {
                                Icon(
                                    imageVector = Icons.Default.PersonAddAlt,
                                    contentDescription = "Account Icon",
                                    modifier = Modifier
                                        .requiredSize(100.dp),
                                    tint = Color.White
                                )
                            }
                        )

                    }
                    val facultyGlyphic = androidx.compose.ui.text.font.FontFamily.Monospace

                    Text(
                        "Pay & Transfer",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.8f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Payments,
                            "Menu",
                            50.dp,
                            true,
                            "Send Money",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Savings,
                            "Menu",
                            50.dp,
                            true,
                            "Savings",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Wallet,
                            "Menu",
                            50.dp,
                            true,
                            "Wallet",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.AddCard,
                            "Menu",
                            50.dp,
                            true,
                            "Passbook",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "UPI",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.bhim_upi,
                            "Pay to UPI",
                            50.dp,
                            true,
                            "Set up UPI",
                            navController,
                            "set_pin_screen"
                        )

                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.upi_pay_100dp,
                            "Menu",
                            50.dp,
                            true,
                            "Pay to UPI ID",
                            navController,
                            navigateToUPI
                        )

                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.gpay,
                            "Menu",
                            50.dp,
                            true,
                            "Google Pay",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.paypal_logo,
                            "Menu",
                            50.dp,
                            true,
                            "Paypal",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "Bills & Recharge",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.AccountCircle,
                            "Menu",
                            50.dp,
                            true,
                            "Pay to Contacts",
                            navController,
                            navigateToContacts
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Autorenew,
                            "Menu",
                            50.dp,
                            true,
                            "Self Transfer",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Shield,
                            "Menu",
                            50.dp,
                            true,
                            "Secure Transfer",
                            navController,
                            navigateToPay
                        )

                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.BookmarkAdded,
                            "Menu",
                            50.dp,
                            true,
                            "Cheques",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "Services",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    )
                    {
                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.visa,
                            "Menu",
                            50.dp,
                            true,
                            "VISA",
                            navController,
                            "dashboard"
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.BookmarkAdded,
                            "Menu",
                            50.dp,
                            true,
                            "Cheques",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.FoodBank,
                            "Menu",
                            50.dp,
                            true,
                            "Net Banking",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Rotate90DegreesCw,
                            "Menu",
                            50.dp,
                            true,
                            "Autopay",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "Cards",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    )
                    {
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CreditCard,
                            "Menu",
                            50.dp,
                            true,
                            "My Cards",
                            navController,
                            "my_cards"
                        )
                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.card_exchange,
                            "Menu",
                            50.dp,
                            true,
                            "Card Exchange",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CreditCard,
                            "Menu",
                            50.dp,
                            true,
                            "Change CVV",
                            navController,
                            "change_cvv"
                        )
                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.atm_100dp,
                            "Menu",
                            50.dp,
                            true,
                            "ATM card",
                            navController,
                            "pay_with_card"
                        )
                    }

                    Text(
                        "Currency",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    )
                    {
                        summonUserIcon2(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            R.drawable.banknotes,
                            "Menu",
                            50.dp,
                            true,
                            "Banknotes",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Euro,
                            "Menu",
                            50.dp,
                            true,
                            "Euro",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CurrencyExchange,
                            "Menu",
                            50.dp,
                            true,
                            "Currency Exchange",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CurrencyRupee,
                            "Menu",
                            50.dp,
                            true,
                            "Rupee",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "Shopping",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    )
                    {
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.ShoppingCart,
                            "Menu",
                            50.dp,
                            true,
                            "Fast Shop",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CreditCard,
                            "Menu",
                            50.dp,
                            true,
                            "Buy with Card",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Receipt,
                            "Menu",
                            50.dp,
                            true,
                            "Reciept",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CreditCardOff,
                            "Menu",
                            50.dp,
                            true,
                            "No Credit Card",
                            navController,
                            navigateToPay
                        )
                    }

                    Text(
                        "Crypto",
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 0.dp, 10.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .size(360.dp, 120.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 3.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.7f),
                                        Color.White.copy(alpha = 0.2f),
                                    ),
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .hazeEffect(
                                state = hazeStateBackground,
                                style = HazeStyle(
                                    White,
                                    tint = HazeTint(
                                        Color(128, 128, 128, 50),
                                        BlendMode.Luminosity),
                                    blurRadius = 30.dp,
                                    noiseFactor = 40f)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    )
                    {
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CurrencyBitcoin,
                            "Bitcoin",
                            50.dp,
                            true,
                            "Bitcoin",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.CurrencyBitcoin,
                            "Menu",
                            50.dp,
                            true,
                            "Exchange Crypto",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Lock,
                            "Menu",
                            50.dp,
                            true,
                            "Encryption",
                            navController,
                            navigateToPay
                        )
                        summonUserIcon(
                            80.dp,
                            120.dp,
                            Shapes().medium,
                            Color.Transparent,
                            Color(103, 104, 236, 255),
                            Icons.Default.Payments,
                            "Menu",
                            50.dp,
                            true,
                            "Payment Protection",
                            navController,
                            navigateToPay
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .size(0.dp, 120.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onTabSelected(tab)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = tab.icon, contentDescription = "tab ${tab.title}")
                    Text(text = tab.title)
                }
            }
        }
    }
}