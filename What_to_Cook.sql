CREATE DATABASE IF NOT EXISTS foodmaterial;
USE foodmaterial;

CREATE TABLE IF NOT EXISTS choose_food (
    id INT PRIMARY KEY,
    book_id INT NOT NULL,
    createtime DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS cook_book (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    material TEXT NOT NULL,
    steps TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS food_material (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO food_material (id, name) VALUES 
(1, 'Chicken'), 
(2, 'Onion'), 
(3, 'Cucumber'), 
(4, 'Pork'), 
(5, 'Green bean'), 
(6, 'Tofu'),
(7, 'Chilie'),
(8, 'Cauliflower'),
(9, 'Egg'), 
(10, 'Tomato'),
(11, 'Shrimp'),
(12, 'Scallions'),
(13, 'Potato'),
(14, 'Carrot'),
(15, 'Pepper'),
(16, 'Potato')
ON DUPLICATE KEY UPDATE 
    name = VALUES(name);
    

INSERT INTO cook_book (id, name, material, steps) VALUES 
(1, 'Sweet and sour chicken', 'chicken, onion', 
'1. For the sauce, put the red pepper, chunks of chilli and pineapple juice in a pan and bring to the boil. Cover and cook for 10 mins, then purée in a food processor. Return to the pan with the pineapple chunks, chilli halves, star anise, tamarind, sugar and vinegar. Gently simmer for 20-30 mins until reduced and sticky. Keep warm, or reheat to serve.\n\
2. Fill a large pan 1cm deep with oil and heat until shimmering. Whisk the soda water and 100ml cold water into the self-raising flour with a little salt. Tip the cornflour onto a plate, line a tray with kitchen paper and turn on the oven to low.\n\
3. Stir the batter well. Dust the chicken with cornflour, then dip into the batter. One at a time, lower into the hot oil (about 5-6 every batch). Turn up the heat to keep the chicken frying, if needed, and cook for 5-6 mins, turning once. When cooked, drain on the tray, and keep warm in the oven. Repeat with the remaining chicken. Stack onto a plate with the warm sauce on the side, and scatter with shredded spring onions.'),

(2, 'Smashed Cucumber Salad', 'cucumber', 
'1. Bash the cucumbers until they split open on the sides (see video) using something heavy like the smooth side of a meat mallet, rolling pin, pestle or even a can.\n\
2. Cut into 2.5cm/1" chunks then place in a bowl.\n\
3. Sprinkle with salt, toss, leave for 20 minutes.\n\
4. Drain excess liquid in the bowl.\n\
5. Shake or mix Dressing well.\n\
6. Drizzle over cucumbers, sprinkle with coriander and green onions. Toss and serve immediately.'),

(3, 'Pork Stir Fry with Green Beans', 'pork, green bean', 
'1. Mix Sauce ingredients in a bowl.\n\
2. Trim the tough end of the beans, then chop into 2 - 2.5cm / 4/5 - 1" pieces.\n\
3. Charred Beans: Heat 1 1/2 tbsp oil in a heavy based skillet over high heat (I use cast iron) until smoking. Add beans, spread out to cover base. Leave for 1 minute. Quick stir, spread out, cook for 30 seconds. Stir, then leave for 30 seconds, then repeat once more (so 2 1/2 minutes in total cook time) until beans are charred but tender crisp (not withered and floppy). Remove into bowl.\n\
4. Turn heat down to medium high, add 1 tbsp oil. Add onion, then garlic and ginger. Cook for 1 minute until edges of onion are golden.\n\
5. Turn heat back up to high. Add pork and cook, breaking it up as you go. Cook for 2 minutes until the pork is cooked through, then add Sauce. Cook for 30 seconds, then add beans and stir for another 30 seconds.'),

(4, 'Mapo Tofu', 'tofu, chilie, pork', 
'1. Heat cooking oil and Sichuan oil in wok or pan, put chili bean paste, spicy black bean, sauté it.\n\
2. Add ginger, garlic, chili powder and sauté for a while.\n\
3. Add dark soy and water.\n\
4. Put silken tofu, leeks, spring onion, and add sugar, let it boil for 2 minutes.\n\
5. Meanwhile take a stainless steel bowl put potato starch and water to prepare thickening starch.\n\
6. Adjust the thickening with potato starch and add it.\n\
7. Serve hot'),

(5, 'Bang Bang Cauliflower', 'cauliflower', 
'1. In a large bowl, whisk together olive oil, sweet chili sauce, Sriracha, lime juice, and garlic.\n\
2. Add cauliflower to the bowl, season generously with salt and pepper, and toss well to combine.\n\
3. Place one third of the cauliflower in a single layer in the basket of the Air Fryer. Cook at 360º for 12 minutes, tossing halfway through.'),

(6, 'Scrambled Eggs with Tomatoes', 'egg, tomato', 
'1. Crack the eggs in a bowl. Add some salt and beat them.\n\
2. Heat enough oil in a pan, toss the eggs in and slightly scramble it till 70% cooked then transfer to a plate, set it aside.\n\
3. Prepare tomatoes: chop and slice the tomatoes in small chunks.\n\
4. Drizzle the rest oil in the pan, toss in the chopped tomatoes, stir and add some salt. Let it cook for about 2 minutes, or till the tomatoes start to reduce in size a bit and the juices come out.\n\
5. Add the eggs back into the pan, give it a quick stir so the tomato juice penetrates into eggs.'),

(7, 'Shrimp and Egg Stir-fry', 'egg, shrimp, scallions', 
'1. Put shrimp into a bowl then add salt, ground white pepper and starch. Mix with chopsticks until well combined.\n\
2. Crack all the eggs into a bowl. Then add salt, white pepper, sesame oil and a little water. Use chopsticks to beat until it reaches an even, smooth consistency.\n\
3. Add cooking oil to a non-stick pan/wok over medium heat. Lay the marinated shrimp in a single layer to fry. Once the first side turns pink, flip over. Turn off the heat as soon as the second side becomes pink too. Transfer the shrimp into the egg mixture leaving the oil in the pan.\n\
4. Pour everything into the pan/wok then turn on the heat again to the lowest setting. As soon as the bottom part starts to solidify, use a spatula to gently scrape and push the egg. This helps the liquid part to reach the hot pan. Keep doing so until most of the egg has turned into curd-like mass but still looks a little underdone.\n\
5. Sprinkle chopped scallions and dish out immediately.'),

(8, 'Chinese Stir Fried Shredded Potatoes', 'potato, carrot, pepper', 
'1. Fill a bowl with cold water. You’ll be soaking the slivered potatoes in the water later. This prevents the potatoes from oxidizing after they’re cut and washes away some of the excess starch.\n\
2. Cut the potato in half, crosswise. Lay the potato halves flat side down on your chopping board. Slice each half into thin planks, slightly less than 1/8-inch thick. Stack a few of the planks and slice into thin slivers. Continue until you have finished slicing all the potatoes. Transfer the slivered potatoes to the bowl with cold water.\n\
3. Peel the carrot and cut them into 3-inch sections. Slice each section into matchstick pieces.\n\
4. When you are ready to cook the potatoes, drain them from the water.\n\
5. Heat the canola oil in a wok over medium to medium-high heat. Add the garlic, ginger, Sichuan peppercorns, and red pepper flakes, if using. Cook for 30 seconds, until fragrant.\n\
6. Next, add the potato slivers and cook for about 2 minutes, stirring occasionally. Add the carrots, bell pepper, white pepper, and salt. Stir to combine. Cook for another 2 to 3 minutes. The outside of the potato should be slightly soft but the inside still crisp.\n\
7. Transfer the potatoes to a plate. Finish the dish with a light drizzle of chili oil, if you like.')
ON DUPLICATE KEY UPDATE 
    name = VALUES(name),
    material = VALUES(material),
    steps = VALUES(steps);