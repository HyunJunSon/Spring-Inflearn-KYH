package kimyh.itemservice.domain.domain;

import kimyh.itemservice.domain.item.Item;
import kimyh.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {

        Item itemA = new Item("ItemA", 10000, 10);
        Item saved = itemRepository.save(itemA);
        Item findItem = itemRepository.findById(itemA.getId());

        Assertions.assertThat(itemA).isEqualTo(findItem);

    }

    @Test
    void findAll() {

        Item itemA = new Item("ItemA", 10000, 10);
        Item itemB = new Item("ItemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        Assertions.assertThat(itemRepository.findAll().size()).isEqualTo(2);
        Assertions.assertThat(itemRepository.findAll()).contains(itemA, itemB);
    }

    @Test
    void updateItem() {

        Item itemA = new Item("ItemA", 10000, 10);
        itemRepository.save(itemA);
        Assertions.assertThat(itemRepository.findById(itemA.getId())).isEqualTo(itemA);

        Item itemB = new Item("ItemB", 20000, 20);
        itemRepository.update(itemA.getId(), itemB);

        Item findItem = itemRepository.findById(itemA.getId());
        Assertions.assertThat(findItem.getItemName()).isEqualTo(itemA.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(itemA.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(itemA.getQuantity());

    }
}
