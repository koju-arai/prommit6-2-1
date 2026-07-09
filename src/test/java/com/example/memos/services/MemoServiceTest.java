package com.example.memos.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.junit.jupiter.api.Test;
import com.example.memos.repositories.MemoRepository;
import com.example.memos.services.MemoService;
import com.example.memos.models.entities.Memos;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;

@DisplayName("MemoServiceのテスト")
@ExtendWith(MockitoExtension.class)
class MemoServiceTest {
	@Mock
	private MemoRepository memoRepository;
	@InjectMocks
	private MemoService memoService;
	
	@DisplayName("findByIdの引数が存在するIDの場合,エンティティを返す")
	@Test
	void testFindById_存在するID() {
		Memos memo = new Memos();
		when(memoRepository.findById(1L))
			.thenReturn(Optional.of(memo));
		
		Optional<Memos> result = memoService.findById(1L);
		
		assertTrue(result.isPresent());
		assertSame(memo, result.get());
		
		verify(memoRepository).findById(1L);
	}
	
	@DisplayName("findByIdの引数が存在しないIDの場合、何も返さない")
	@Test
	void testFindById_存在しないID() {
		Memos memo = new Memos();
		when(memoRepository.findById(999L))
			.thenReturn(Optional.empty());
		
		Optional<Memos> result = memoService.findById(999L);
		
		assertTrue(result.isEmpty());
		verify(memoRepository).findById(999L);
	}
	
	@DisplayName("deleteByIdの引数が存在するIDの場合、削除処理")
	@Test
	void testDeleteById_存在するID() {
		Memos memo = new Memos();
		when(memoRepository.findById(1L))
			.thenReturn(Optional.of(memo));
		
		memoService.deleteById(1L);
		
		assertTrue(memo.isDeleted());
		verify(memoRepository).findById(1L);
		verify(memoRepository).save(memo);
	}
	
	@DisplayName("deleteByIdの引数が存在しないIDの場合、エラー表示")
	@Test
	void testDeleteById_存在しないID() {
		Memos memo =new Memos();
		when(memoRepository.findById(999L))
			.thenReturn(Optional.empty());
		
		assertThrows(NoSuchElementException.class, () -> {memoService.deleteById(999L);});
		
		verify(memoRepository).findById(999L);
		verify(memoRepository, never()).save(any(Memos.class));
	}
	
	@DisplayName("saveの使用")
	@Test
	void testSave() {
		Memos memo = new Memos();
		memoService.save(memo);
		verify(memoRepository).save(memo);
	}
	
	@DisplayName("createSortの引数が0の場合、昇順で一覧表示")
	@Test
	void testCreateSort_sort_0() {
		Sort sort = memoService.createSort(0);
		Sort.Order order= sort.getOrderFor("updatedAt");
		
		assertNotNull(order);
		assertEquals(Sort.Direction.ASC, order.getDirection());
	}
	
	@DisplayName("createSortの引数が1の場合、降順で一覧表示")
	@Test
	void testCreateSort_sort_1() {
		Sort sort = memoService.createSort(1);
		Sort.Order order = sort.getOrderFor("updatedAt");
		
		assertNotNull(order);
		assertEquals(Sort.Direction.DESC, order.getDirection());
	}
	
	@DisplayName("createSortの引数がnullの場合、降順で一覧表示")
	@Test
	void testCreateSort_sort_null() {
		Sort sort = memoService.createSort(null);
		Sort.Order order = sort.getOrderFor("updatedAt");
		
		assertNotNull(order);
		assertEquals(Sort.Direction.DESC, order.getDirection());
	}
	
	@DisplayName("findAllの検索条件なしの場合、isDeletedがfalseのものが降順で一覧表示")
	@Test
	void testFindAll_null() {
		Memos memo1 = new Memos();
		Memos memo2 = new Memos();
		
		when(memoRepository.findAll(
			any(Specification.class),
			any(Sort.class)
			)).thenReturn(List.of(memo1,memo2));
		
		List<Memos> result = memoService.findAll(null, null, null, null, null);
		
		assertEquals(2, result.size());
		
		ArgumentCaptor<Sort> sortCaptor = ArgumentCaptor.forClass(Sort.class);
		verify(memoRepository).findAll(
				any(Specification.class),
				sortCaptor.capture()
		);
		Sort sort = sortCaptor.getValue();
		Sort.Order oder = sort.getOrderFor("updatedAt");
		
		assertNotNull(oder);
		assertEquals(Sort.Direction.DESC, oder.getDirection());
	}
	
	@DisplayName("findAllの検索条件ありでsort = 0の場合、isDeletedがfalseのものが, 降順で一覧表示")
	@Test
	void  testFindAll_sort_0() {
		Memos memo1 = new Memos();
		Memos memo2 = new Memos();
		
		when(memoRepository.findAll(
			any(Specification.class),
			any(Sort.class)
		)).thenReturn(List.of(memo1, memo2));
		
		List<Memos> result = memoService.findAll(
			"あ",
			List.of(1L,2L),
			LocalDate.of(2026, 6, 15),
			LocalDate.of(2026, 7, 1),
			0);
		
		assertEquals(2, result.size());
		
		ArgumentCaptor<Sort> sortCaptor = ArgumentCaptor.forClass(Sort.class);
		verify(memoRepository).findAll(
				any(Specification.class),
				sortCaptor.capture()
		);
		Sort sort = sortCaptor.getValue();
		Sort.Order oder = sort.getOrderFor("updatedAt");
		
		assertNotNull(oder);
		assertEquals(Sort.Direction.ASC, oder.getDirection());
	}

}
